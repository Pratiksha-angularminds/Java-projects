package com.pratiksha.authentication.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.pratiksha.authentication.models.UserModel;

import com.pratiksha.authentication.services.FileService;

@RestController
public class UserController
{
    @Autowired
    private FileService fileService; 

    // @Value("${project.image}")
    // private String path;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException 
    {

        // String temp = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
        
        System.out.println(file.getOriginalFilename());
        UserModel userModel = fileService.addFile(file);
        return ResponseEntity.ok(userModel);
    }

    @PostMapping("/uploadMultiple")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile files[]) throws IOException 
    {

        // String temp = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
  
        UserModel userModel = fileService.addMultipleFile(files);
        return ResponseEntity.ok(userModel);
    }
}
