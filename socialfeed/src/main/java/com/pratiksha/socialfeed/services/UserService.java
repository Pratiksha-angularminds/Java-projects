package com.pratiksha.socialfeed.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.payload.request.EditProfileRequest;
import com.pratiksha.socialfeed.repositories.UserRepository;

@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepository;
    
    public final String UPLOAD_DIR = new ClassPathResource("static/images/").getFile().getAbsolutePath();
 
    public UserService() throws IOException
    {

    }

    
    //-------------------------------------------FOR SINGLE FILE-----------------------------
    public String addFile(MultipartFile multipartFile) throws IOException 
    {

        String filename = Calendar.getInstance().get(Calendar.MILLISECOND) + multipartFile.getOriginalFilename().trim();
        
        Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR + "/" + filename), StandardCopyOption.REPLACE_EXISTING);

        String filepath = ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(filename).toUriString();
        
        return filepath;
    }

    //-------------------------edit profile--------------------------
    public UserModel editProfile(HashMap formdata,MultipartFile file)
    {
        UserModel user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        
        user.setName(formdata.get("name"));
        
        // user.setEmail(editProfileRequest.getEmail());
        // user.setGender(editProfileRequest.getGender());
        // user.setDob(editProfileRequest.getDob());
        // user.setMobile(editProfileRequest.getMobile());

        if(editProfileRequest.getRemoveImg() == true)
        {
            
            user.setRemoveImg(editProfileRequest.getRemoveImg());
            user.setProfileImg("");
           
        } 
        else
        {
            user.setRemoveImg(editProfileRequest.getRemoveImg());
            user.setProfileImg(editProfileRequest.getProfileImg());
        }
    
        System.out.println("--------------------"+user);
        userRepository.save(user);
        return user;
    }
}
