package com.chatop.service;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PictureStorageService {

    private final static Logger logger = LoggerFactory.getLogger(PictureStorageService.class);

    @Autowired
    private HttpServletRequest request;

    public String savePicture(MultipartFile picture) throws IOException {
        String pictureFolderLocation = "/pictures/";

        if (!picture.isEmpty()) {
            String realPictureFolderLocation =  request.getServletContext().getRealPath(pictureFolderLocation);

            if(!new File(realPictureFolderLocation).exists()) {
                logger.info("Directory for picture does not exist, creating one at: {}", realPictureFolderLocation);
                new File(realPictureFolderLocation).mkdir();
            }
            String originalName = StringUtils.cleanPath(picture.getOriginalFilename());
            String filePath = realPictureFolderLocation + originalName;
            logger.info("Picture will be stored at: {}", realPictureFolderLocation);
            File dest = new File(filePath);
            picture.transferTo(dest);
            logger.info("Picture {} successfully created!", originalName);

            return filePath;
        }

        logger.error("Picture couldn't be saved");
        throw new IOException("Couldn't save picture");
    }
}
