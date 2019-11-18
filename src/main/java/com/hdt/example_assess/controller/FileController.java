package com.hdt.example_assess.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@CrossOrigin("*")
@RestController
public class FileController {

//    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity uploadFile(@RequestParam MultipartFile file) throws IOException {
//        logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
//        File newFile = new File("E:/_AJava_WorkPlace/example_assess/src/main/resources/static/image/" + file.getOriginalFilename());
//        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//        fileOutputStream.write(file.getBytes());
//        fileOutputStream.close();
//        return ResponseEntity.ok().build();
//    }
//    @PostMapping(value = "/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity uploadFile(@RequestParam MultipartFile[] files) throws IOException {
//        for (int i = 0; i < files.length; i++) {
//            logger.info(String.format("File name '%s' uploaded successfully.", files[i].getOriginalFilename()));
//            files[i].getOriginalFilename();
//            File newFile = new File("E:\\_AJava_WorkPlace\\example_assess\\src\\main\\resources\\static\\image" +  files[i].getOriginalFilename());
//            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//            fileOutputStream.write( files[i].getBytes());
//            fileOutputStream.close();
//        }
//
//        return ResponseEntity.ok().build();
//    }
}
