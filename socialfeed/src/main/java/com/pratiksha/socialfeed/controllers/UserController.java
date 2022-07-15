package com.pratiksha.socialfeed.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.payload.request.EditProfileRequest;
import com.pratiksha.socialfeed.repositories.UserRepository;
import com.pratiksha.socialfeed.services.UserService;

@RestController
public class UserController 
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //---------------------------------get all users-----------------------------------
    @GetMapping("/users")
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


    //--------------------------------------edit profile----------------------------------
    @PatchMapping("/users/edit-profile")
    public ResponseEntity<?> editProfile(@Valid @RequestParam Map<EditProfileRequest> formdata,@RequestParam("profileImg") MultipartFile file)
    {
        // UserModel user = userService.editProfile(formdata,file);
        System.out.println("------------"+formdata + file.getOriginalFilename());

        Map<String, Object> model = new HashMap<>();
        model.put("message", "User updated successfully");
        // model.put("user", user);

        return ResponseEntity.ok(model);
    }

}
