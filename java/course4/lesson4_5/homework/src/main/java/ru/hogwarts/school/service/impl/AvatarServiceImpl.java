package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentMapper;
import ru.hogwarts.school.exception.NotFoundException;
import ru.hogwarts.school.exception.UnableToCreateException;
import ru.hogwarts.school.exception.UnableToDeleteException;
import ru.hogwarts.school.exception.UnableToUploadFileException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static ru.hogwarts.school.service.impl.StudentServiceImpl.CREATED;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatars.folder}")
    private String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final StudentMapper mapper;


    @Override
    public void uploadAvatar(Long id, MultipartFile avatarFile) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        Student student;
        try {
            student = studentRepository.getById(id);
        } catch (Exception e) {
            throw new NotFoundException("Student", "id", id, e);
        }

        Path newPath = getNewPath(avatarFile, student);
        copyFile(avatarFile, newPath);
        fillAndSaveAvatar(avatarFile, student, newPath);
    }

    @Override
    public void deleteAvatarById(Long id) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        Optional<Avatar> avatar = avatarRepository.findById(id);
        try {
            avatarRepository.deleteById(id);
            Files.deleteIfExists(Path.of(avatar.get().getFilePath()));
        } catch (RuntimeException | IOException e) {
            throw new UnableToDeleteException("Avatar", "id", id, e);
        }
    }

    @Override
    public Avatar getOrCreateAvatar(Long id) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return avatarRepository.findById(id).orElse(new Avatar());
    }

    @Override
    public Avatar findAvatarById(Long id) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            return avatarRepository.getById(id);
        } catch (Exception e) {
            throw new NotFoundException("Avatar for Student", "id", id, e);
        }
    }

    @Override
    public List<Avatar> getAllAvatars(int pageNumber, int pageSize) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        PageRequest pageRequest = PageRequest.of(--pageNumber, pageSize,
                Sort.Direction.ASC, "fileSize");
        return avatarRepository.findAll(pageRequest).getContent();
    }

    private Path getNewPath(MultipartFile avatarFile, Student student) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        Path filePath;
        try {
            filePath = Path.of(avatarsDir, mapper.toDto(student) + "." +
                    getExtension(Objects.requireNonNull(avatarFile.getOriginalFilename())));
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new UnableToUploadFileException(e);
        }
        return filePath;
    }

    private void copyFile(MultipartFile avatarFile, Path filePath) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(os)
        ) {
            bis.transferTo(bos);
        } catch (Exception e) {
            throw new UnableToUploadFileException(e);
        }
    }

    private void fillAndSaveAvatar(MultipartFile avatarFile, Student student, Path filePath) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        Avatar avatar = getOrCreateAvatar(student.getId());
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());

        try {
            avatar.setData(avatarFile.getBytes());
            avatarRepository.save(avatar);
        } catch (Exception e) {
            throw new UnableToCreateException(e);
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}
