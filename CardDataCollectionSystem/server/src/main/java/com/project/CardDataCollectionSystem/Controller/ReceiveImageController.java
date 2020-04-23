package com.project.CardDataCollectionSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import com.project.CardDataCollectionSystem.Entity.Image;

import com.project.CardDataCollectionSystem.Repository.ImageRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class ReceiveImageController {

    @Autowired 
    private ImageRepository imagerepository;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        
        String message = "";
        try {

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            
            Image dbFile = new Image(fileName, file.getContentType(), file.getBytes());

            imagerepository.save(dbFile);


            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return message;
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return message;
        }
    }

}