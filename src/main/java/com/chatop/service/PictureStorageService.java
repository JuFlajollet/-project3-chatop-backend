package com.chatop.service;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PictureStorageService {

    private final static Logger logger = LoggerFactory.getLogger(PictureStorageService.class);
    private final Path root = Paths.get("upload-dir");

    @Value("${server.port: 8080}")
    private String serverPort;

    @Autowired
    private HttpServletRequest request;

    private void initUploadDir() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public Resource loadAsResource(String pictureName) throws IOException {
        if(!pictureName.isEmpty()) {
            try {
                logger.info("Trying to load picture {}", pictureName);
                Path file = this.root.resolve(pictureName);
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    logger.info("Picture successfully loaded {}", pictureName);
                    return resource;
                } else {
                    throw new IOException("Could not read file: " + pictureName);
                }
            } catch (IOException e) {
                throw new IOException("Could not read file: " + pictureName);
            }
        }

        logger.error("Picture is empty");
        throw new IOException("Picture is empty");
    }

    public String storePicture(MultipartFile picture) throws IOException {
        if (!picture.isEmpty()) {
            if(isValidPictureExtension(picture)) {
                initUploadDir();

                String uniquePictureName = UUID.randomUUID() + "_" + picture.getOriginalFilename();
                Path filePath = this.root.resolve(uniquePictureName);
                logger.info("Picture will be stored at: {}", root);

                try {
                    Files.copy(picture.getInputStream(), filePath);
                    logger.info("Picture {} successfully created!", uniquePictureName);

                    String storedPath = "http://" + InetAddress.getLocalHost().getHostName() + ":" + serverPort + "/api/pictures/" + uniquePictureName;

                    return storedPath;
                } catch (Exception e) {
                    if (e instanceof FileAlreadyExistsException) {
                        logger.error("Picture {} already exists", uniquePictureName);
                        throw new RuntimeException("A picture with that name already exists.");
                    }
                    logger.error("Picture couldn't be stored");
                    throw new RuntimeException(e.getMessage());
                }
            }

            logger.error("Picture {} is not of valid extension", picture.getOriginalFilename());
            throw new IOException("Picture is not of valid extension");
        }

        logger.error("Picture is empty");
        throw new IOException("Picture is empty");
    }

    private boolean isValidPictureExtension(MultipartFile picture){
        if (!picture.isEmpty()) {
            String fileExtensions = ".jpg, .png";
            String pictureName = picture.getOriginalFilename();
            int lastIndex = pictureName.lastIndexOf('.');
            String substring = pictureName.substring(lastIndex);

            return fileExtensions.contains(substring.toLowerCase());
        }
        return false;
    }
}
