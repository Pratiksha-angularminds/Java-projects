package com.pratiksha.authentication.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.pratiksha.authentication.models.UserModel;
import com.pratiksha.authentication.repository.UserRepository;
import com.pratiksha.authentication.utils.JwtUtil;

@Service
public class UserService extends OidcUserService  implements UserDetailsService 
{
    @Autowired
    private UserRepository userRepository;  
    
    @Autowired
    private EmailService emailService;

   

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {
        UserModel foundedUser = userRepository.findByEmail(email);
       System.out.println("foundedUser:-------"+foundedUser);

        // return UserPrincipal.create(foundedUser);
        if(foundedUser == null)
            return null;

       
        String name = foundedUser.getEmail();
        System.out.println("-------------------------"+name);
        String pwd = foundedUser.getPassword();

        return new User(name,pwd,new ArrayList<>());
    }

   
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException 
    {
       
        final OidcUser oidcUser = super.loadUser(userRequest);
        return oidcUser;
    }

    public Map<String, Object> forgotPassword(String email)
    {
        UserModel user = userRepository.findByEmail(email);
        Map<String, Object> model = new HashMap<>();

        if(user == null)
        {
            return null;
        }

        String token = new JwtUtil().generateForgotPasswordToken(user.getId());
        
        String resetPasswordLink =  "http://localhost:8080/reset-password?token=" + token;
        String result =  emailService.sendMail(email,resetPasswordLink);
        if(result == "true")
        {
            
            model.put("token", token);
            model.put("message","we have sent an email to your account Successfully!!");
            return model;
        }
        else
        {
            model.put("message", "Error in sending mail!!");
            return model;
        }
    }

    public void processOAuthPostLogin(String email) 
    {
        UserModel user = userRepository.findByEmail(email);
         
        if (user == null) 
        {
            
            UserModel newUser = new UserModel();
            newUser.setEmail(email);
            // newUser.setProvider(Provider.GOOGLE);
            userRepository.save(newUser);
        }
     
    }
}
