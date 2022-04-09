package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.FacultyMapper;
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

@Service
@Transactional
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatars.folder}")
    private String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final FacultyMapper mapper;

    @Override
    public void uploadAvatar(Long id, MultipartFile avatarFile) {
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
        return avatarRepository.findById(id).orElse(new Avatar());
    }

    @Override
    public Avatar findAvatarById(Long id) {
        try {
            return avatarRepository.getById(id);
        } catch (Exception e) {
            throw new NotFoundException("Avatar for Student", "id", id, e);
        }
    }

    private Path getNewPath(MultipartFile avatarFile, Student student) {
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

    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepository.findAll();
    }

}
