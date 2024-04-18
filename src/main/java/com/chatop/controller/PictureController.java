package com.chatop.controller;

import com.chatop.service.PictureStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pictures")
public class PictureController {
    @Autowired
    PictureStorageService storageService;

    @Operation(summary = "Get a stored picture", description = "Return the stored picture")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Picture successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - There was an issue with the picture"),
    })
    @GetMapping(value = "/{pictureName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Resource> getFile(@PathVariable(value="pictureName") String pictureName) throws IOException {
        Resource file = storageService.loadAsResource(pictureName);

        return ResponseEntity.ok().body(file);
    }
}
