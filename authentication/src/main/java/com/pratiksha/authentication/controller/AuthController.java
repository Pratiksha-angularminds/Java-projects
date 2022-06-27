package com.pratiksha.authentication.controller;

import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.pratiksha.authentication.models.AuthenticationRequest;
import com.pratiksha.authentication.models.AuthenticationResponse;
import com.pratiksha.authentication.models.UserModel;
import com.pratiksha.authentication.repository.UserRepository;

import com.pratiksha.authentication.services.UserService;
import com.pratiksha.authentication.utils.JwtUtil;


@RestController
public class AuthController 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder; 
    
   

    // @GetMapping("/dashboard")
    // public ResponseEntity<?> testingToken()
    // {
    //     return ResponseEntity.ok(new AuthenticationResponse("Welcome to Dashboard " + SecurityContextHolder.getContext().getAuthentication().getName()));
    // }
    
    @GetMapping("/dashboard")
    public Object testingToken()
    {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    @PostMapping("/subs")
    public ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest)
    {
        UserModel userModel = new UserModel();

        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        UserModel userExists = userRepository.findByUsername(username);

        if (userExists != null) 
        {
            // return new ResponseEntity<>("Username already registered,Plz try with another username!!",HttpStatus.BAD_REQUEST);
            return ResponseEntity.ok(new AuthenticationResponse("Username already registered,Plz try with another username!!"));
        }
        userModel.setUsername(username);
        userModel.setPassword(passwordEncoder.encode(password));

        try
        {
            userRepository.save(userModel);
        }catch(Exception e)
        {
            return ResponseEntity.ok(new AuthenticationResponse("Error during client subscription"+authenticationRequest.getUsername()));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Success!!"+authenticationRequest.getUsername()));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticateClient(@RequestBody AuthenticationRequest authenticationRequest)
    {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch(Exception e)
        {
            return ResponseEntity.ok(new AuthenticationResponse("Failed!!"+authenticationRequest.getUsername()));
        }

        UserDetails loadedUser = this.userService.loadUserByUsername(username);
        String generatedToken = this.jwtUtil.generateToken(loadedUser);
        
        UserModel user = userRepository.findByUsername(username);
        
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("token", generatedToken);
        // return model;
        return ResponseEntity.ok(model);
    }

    
}