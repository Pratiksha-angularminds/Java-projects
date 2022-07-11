package com.pratiksha.socialfeed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.repositories.UserRepository;

@RestController
public class UserController 
{

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers()
    {
        List<UserModel> user = userRepository.findAll();
        if(user.size() >0)
        {
            return ResponseEntity.ok(user);
        }
        else
        {
            return new ResponseEntity<>("No student available",HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserModel user)
    {
        UserModel save = userRepository.save(user);
        return ResponseEntity.ok(save);
    }

}
