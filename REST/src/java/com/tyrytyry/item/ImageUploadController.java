package com.tyrytyry.item;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
public class ImageUploadController {

    private static final String UPLOAD_DIR = "/home/tyrytyry/obszarRoboczy/gity/REST/src/main/resources/static/produkty";

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return "Przesłane zdjęcie jest puste";
        }

        try {

            String fileName = UUID.randomUUID().toString() + "-" + StringUtils.cleanPath(file.getOriginalFilename());


            Path targetPath = Path.of(UPLOAD_DIR, fileName);


            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return "Zdjęcie zostało przesłane i zapisane jako: " + fileName;
        } catch (IOException e) {
            return "Wystąpił błąd podczas przesyłania i zapisywania zdjęcia: " + e.getMessage();
        }
    }
}