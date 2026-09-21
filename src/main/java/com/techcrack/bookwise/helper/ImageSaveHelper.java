package com.techcrack.bookwise.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class ImageSaveHelper {
    @Value("${bookwise.upload-dir}")
    private String uploadDir;

    public ImageSaveHelper() {

    }

    private Path getUploadDirectory() {
        return Paths.get(uploadDir);
    }

    public String saveMultiPartImage(MultipartFile coverImage) throws IOException {
        Path uploadDirectory = getUploadDirectory();

        Files.createDirectories(uploadDirectory);

        String originalFileName =
                coverImage.getOriginalFilename();

        String fileName =
                UUID.randomUUID() + "_" + originalFileName;

        Path filePath =
                uploadDirectory.resolve(fileName);

        Files.copy(
                coverImage.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return uploadDir + "/" + fileName;
    }
}
