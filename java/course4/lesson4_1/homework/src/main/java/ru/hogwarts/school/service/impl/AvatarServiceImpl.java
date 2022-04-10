package ru.hogwarts.school.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.NotFoundException;
import ru.hogwarts.school.exception.UnableToCreateException;
import ru.hogwarts.school.exception.UnableToUploadFileException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.transaction.Transactional;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static ru.hogwarts.school.exception.ApiException.UNABLE_TO_CREATE;
import static ru.hogwarts.school.exception.ApiException.UNABLE_TO_UPLOAD;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatars.folder}")
    private String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final StudentService studentService;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
        this.studentService = studentService;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) {
        Student student = studentService.findStudent(studentId);
        Path newPath = getNewPath(avatarFile, student);
        copyFile(avatarFile, newPath);
        fillAndSaveAvatar(avatarFile, student, newPath);
    }

    @Override
    public Avatar getOrCreateAvatar(Long studentId) {
        return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }

    @Override
    public Avatar getAvatar(Long studentId) {
        return avatarRepository.findAvatarByStudentId(studentId)
                .orElseThrow(() -> new NotFoundException("Avatar for Student", "studentId", studentId));
    }

    @Override
    public List<Avatar> getAvatars(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(--pageNumber, pageSize,
                Sort.Direction.ASC, "fileSize");
        return avatarRepository.findAll(pageRequest).getContent();
    }

    private Path getNewPath(MultipartFile avatarFile, Student student) {
        Path filePath;
        try {
            filePath = Path.of(avatarsDir, student + "." +
                    getExtension(Objects.requireNonNull(avatarFile.getOriginalFilename())));

            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new UnableToUploadFileException(UNABLE_TO_UPLOAD, e);
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
            throw new UnableToUploadFileException(UNABLE_TO_UPLOAD, e);
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
            throw new UnableToCreateException(UNABLE_TO_CREATE, e);
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}
