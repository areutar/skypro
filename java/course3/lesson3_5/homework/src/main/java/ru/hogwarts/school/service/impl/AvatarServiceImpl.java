package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.exception.ApiException;
import ru.hogwarts.school.exception.NotFoundException;
import ru.hogwarts.school.exception.UnableToCreateException;
import ru.hogwarts.school.exception.UnableToUploadFileException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;
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

@Service
@Transactional
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    @Value("${avatars.folder}")
    private String avatarsDir;
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) {
        StudentDto student = studentService.findStudent(studentId);
        Path newPath = getNewPath(avatarFile, student);
        copyFile(avatarFile, newPath);
        fillAndSaveAvatar(avatarFile, studentId, newPath);
    }

    @Override
    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findAvatarByStudentId(studentId).orElse(new Avatar());
    }

    @Override
    public Avatar getAvatar(Long studentId) {
        Avatar avatar = avatarRepository.getAvatarByStudentId(studentId);
        if (avatar == null) {
            throw new NotFoundException("Avatar for Student", "studentId", studentId);
        }
        return avatar;
    }

    private Path getNewPath(MultipartFile avatarFile, StudentDto student) {
        Path filePath;
        try {
            filePath = Path.of(avatarsDir, student + "." +
                    getExtension(Objects.requireNonNull(avatarFile.getOriginalFilename())));
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new UnableToUploadFileException(ApiException.UNABLE_TO_UPLOAD, e);
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
            throw new UnableToUploadFileException(ApiException.UNABLE_TO_UPLOAD, e);
        }
    }

    private void fillAndSaveAvatar(MultipartFile avatarFile, Long studentId, Path filePath) {
        Avatar avatar = findAvatar(studentId);
        Student student = studentRepository.findById(studentId).get();
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());

        try {
            avatar.setData(avatarFile.getBytes());
            avatarRepository.save(avatar);
        } catch (Exception e) {
            throw new UnableToCreateException(ApiException.UNABLE_TO_CREATE, e);
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
