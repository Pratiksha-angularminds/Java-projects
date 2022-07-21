package com.pratiksha.socialfeed.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.payload.request.LoginRequest;
import com.pratiksha.socialfeed.payload.request.RegisterRequest;
import com.pratiksha.socialfeed.repositories.UserRepository;
import com.pratiksha.socialfeed.security.Jwt.JwtUtils;
import com.pratiksha.socialfeed.services.AuthService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController 
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;



    //--------------------------------------REGISTER USER----------------------------------
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerUser) 
    {
        Object msg;
        try
        {
            msg = authService.registerUser(registerUser);
            return ResponseEntity.ok(msg);
            
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    //-----------------------------------LOGIN USER--------------------------------------
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) 
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateToken(authentication);
       
        UserModel user = userRepository.findByEmail(loginRequest.getEmail());
        Map<String, Object> model = new HashMap<>();
        model.put("message", "User Loged in succesfully!!");
        model.put("user", user);
        model.put("token", token);
        
        return ResponseEntity.ok(model);
    }

    //------------------------------------LOGOUT USER---------------------------------------
    @PostMapping("/auth/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) 
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null)
        {      
            session.invalidate();
        }  
        Map<String, Object> model = new HashMap<>();
        model.put("message", "User logged out successfully!!");
        return ResponseEntity.ok(model);
    }
    
}
