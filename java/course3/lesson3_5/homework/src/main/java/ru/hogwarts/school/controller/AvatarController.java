package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AvatarController {
    private final AvatarService avatarService;

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id,
                                               @RequestParam MultipartFile avatar) {
        try {
            avatarService.uploadAvatar(id, avatar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}/avatar")
    public ResponseEntity<Void> deleteAvatarById(@PathVariable Long id) {
        avatarService.deleteAvatarById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatarById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(avatar.getMediaType()))
                .contentLength(avatar.getData().length)
                .body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatarById(id);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(HttpStatus.ACCEPTED.value());
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping(value = "/{id}/avatar-from-file2")
    public ResponseEntity<byte[]> downloadAvatar2(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatarById(id);
        Path path = Path.of(avatar.getFilePath());

        byte[] media;
        try {
            media = Files.readAllBytes(path);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentLength(media.length)
                    .contentType(MediaType.parseMediaType(avatar.getMediaType()))
                    .body(media);
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentLength(e.getMessage().length())
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage().getBytes(StandardCharsets.UTF_8));
        }
    }

    @GetMapping(value = "/avatars")
    public List<Avatar> getAllAvatars() {
        return avatarService.getAllAvatars();
    }

}