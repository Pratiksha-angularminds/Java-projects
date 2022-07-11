package com.pratiksha.authentication.controller;

import java.io.IOException;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
    
   

   
    @GetMapping("/dashboard")
    public Object testingToken()
    {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody AuthenticationRequest authenticationRequest)
    {
        UserModel userModel = new UserModel();

        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        UserModel userExists = userRepository.findByEmail(email);

        if (userExists != null) 
        {
            // return new ResponseEntity<>("Username already registered,Plz try with another username!!",HttpStatus.BAD_REQUEST);
            return ResponseEntity.ok(new AuthenticationResponse("Username already registered,Plz try with another username!!"));
        }
        userModel.setEmail(email);
        userModel.setPassword(passwordEncoder.encode(password));

        try
        {
            userRepository.save(userModel);
        }catch(Exception e)
        {
            return ResponseEntity.ok(new AuthenticationResponse("Error during client subscription"+authenticationRequest.getEmail()));
        }

        return ResponseEntity.ok(new AuthenticationResponse("Success!!"+authenticationRequest.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginClient(@RequestBody AuthenticationRequest authenticationRequest)
    {
        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch(Exception e)
        {
            return ResponseEntity.ok(new AuthenticationResponse("Incorrect Email ID or Password!!"));
        }

        UserDetails loadedUser = userService.loadUserByUsername(email);
        String generatedToken = jwtUtil.generateToken(loadedUser);
        
        UserModel user = userRepository.findByEmail(email);
        
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("token", generatedToken);
        // return model;
        return ResponseEntity.ok(model);
    }

    @GetMapping("/user")
    @ResponseBody
    public  Principal googleLogin(Principal principal)
    {
        return principal;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody AuthenticationRequest authenticationRequest) 
    {
        Map<String,Object> model = userService.forgotPassword(authenticationRequest.getEmail());
        return ResponseEntity.ok(model);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody AuthenticationRequest authenticationRequest) throws IOException 
    {
        String newPassword = authenticationRequest.getNewPassword();
        String confirmPassword = authenticationRequest.getConfirmPassword();
        
        System.out.println("newpass:-"+newPassword + "\n confirmpass: "+ confirmPassword);
        if(!newPassword.equals(confirmPassword))
        {
            System.out.println(newPassword==confirmPassword);
            return ResponseEntity.ok(new AuthenticationResponse("Password does not match!!"));
        }
        else
        {
           
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserModel user = userRepository.findByEmail(email);
                
            
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            return ResponseEntity.ok(new AuthenticationResponse("Password changed successfully!!"));
    
        }
    }
}

// @PostMapping("/sendMail")
// public String
// sendMail(@RequestBody EmailDetails details)
// {
//     String status
//         = emailService.sendSimpleMail(details);

//     return status;
// }