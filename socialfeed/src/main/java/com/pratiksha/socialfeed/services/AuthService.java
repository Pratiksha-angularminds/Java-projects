package com.pratiksha.socialfeed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.payload.request.RegisterRequest;
import com.pratiksha.socialfeed.repositories.UserRepository;

@Service
public class AuthService 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    //-----------------------------------SERVICE TO REGISTER USER-----------------------------
    public Object registerUser(RegisterRequest registerUser)
    {
        if(userRepository.existsByEmail(registerUser.getEmail()))
            {
                return "User already exists!!";
            }
            else
            {
                UserModel savedUser = new UserModel();

                savedUser.setName(registerUser.getName());
                savedUser.setEmail(registerUser.getEmail());
                savedUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
                savedUser.setGender(registerUser.getGender());
                savedUser.setDob(registerUser.getDob());
                savedUser.setMobile(registerUser.getMobile());
                savedUser.setRemoveImg(registerUser.getRemoveImg());
                savedUser.setProfileImg(registerUser.getProfileImg());

                userRepository.save(savedUser);
                return savedUser;
            }
    }
}
