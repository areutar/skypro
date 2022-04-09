package ru.hogwarts.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long id, MultipartFile avatarFile);

    Avatar getOrCreateAvatar(Long id);

    Avatar findAvatarById(Long id);

    List<Avatar> getAllAvatars();

    void deleteAvatarById(Long id);
}
