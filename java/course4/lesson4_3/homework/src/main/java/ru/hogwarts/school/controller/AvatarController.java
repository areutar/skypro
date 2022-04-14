package ru.hogwarts.school.controller;

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
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{student_id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long student_id,
                                               @RequestParam MultipartFile avatar) {
        try {
            avatarService.uploadAvatar(student_id, avatar);
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
        try (OutputStream os = response.getOutputStream()) {
            Avatar avatar = avatarService.findAvatarById(id);
            Path path = Path.of(avatar.getFilePath());
            InputStream is = Files.newInputStream(path);
            response.setStatus(HttpStatus.ACCEPTED.value());
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
            is.close();
        }
    }


    @GetMapping(value = "/{id}/avatar-from-file2")
    public ResponseEntity<byte[]> downloadAvatar2(@PathVariable Long id) {
        try {
            Avatar avatar = avatarService.findAvatarById(id);
            Path path = Path.of(avatar.getFilePath());
            byte[] media = Files.readAllBytes(path);
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

    @GetMapping
    public ResponseEntity<List<Avatar>> getAllAvatars(@RequestParam("page") int pageNumber,
                                                      @RequestParam("size") int pageSize) {
        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }

}
