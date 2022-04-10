package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar getOrCreateAvatar(Long studentId);

    Avatar getAvatar(Long studentId);

    List<Avatar> getAvatars(int pageNumber, int pageSize);
}
