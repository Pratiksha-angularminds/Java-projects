package com.pratiksha.authentication.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.pratiksha.authentication.models.UserModel;
import com.pratiksha.authentication.repository.UserRepository;
import com.pratiksha.authentication.services.FileService;



@RestController
public class UserController
{
    @Autowired
    private FileService fileService; 
    
    @Autowired
    private UserRepository userRepository;


    // @Value("${project.image}")
    // private String path;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers()
    {
        List<UserModel> users = userRepository.findAll();
        System.out.println("-------0000--------"+SecurityContextHolder.getContext().getAuthentication().getName());

        //--------sorting-----------------
        // List<UserModel> users = userRepository.findAll(Sort.by(Direction.DESC,"email"));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserByPagination")
    public ResponseEntity<?> getUserByPagination(@RequestParam int page , @RequestParam int limit)
    {
        Page<UserModel> users = userRepository.findAll(PageRequest.of(page, limit));
        return ResponseEntity.ok(users);
    }


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
