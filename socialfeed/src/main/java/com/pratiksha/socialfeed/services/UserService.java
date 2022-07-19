package com.pratiksha.socialfeed.services;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
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
    public UserModel editProfile(EditProfileRequest editProfileRequest) throws IOException
    {
        UserModel user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        
        if(editProfileRequest.getName() != null)
        {
            user.setName(editProfileRequest.getName());
        }

        if(editProfileRequest.getEmail() != null)
        {
            user.setEmail(editProfileRequest.getEmail());
        }

        if(editProfileRequest.getGender() != null)
        {
            user.setGender(editProfileRequest.getGender());
        }

        if(editProfileRequest.getDob() != null)
        {
            user.setDob(editProfileRequest.getDob());
        }

        if(editProfileRequest.getMobile() != null)
        {
            user.setMobile(editProfileRequest.getMobile());
        }

        if(editProfileRequest.getRemoveImg() == true)
        {
            user.setRemoveImg(editProfileRequest.getRemoveImg());
            user.setProfileImg("");
        } 
        else if(editProfileRequest.getProfileImg() != null)
        {
            String filePath = addFile(editProfileRequest.getProfileImg());
            user.setRemoveImg(editProfileRequest.getRemoveImg());
            user.setProfileImg(filePath);
        }
    
        userRepository.save(user);
        return user;

    }
}

// editProfileRequest.forEach((k,v) ->
// {
//     Field field = ReflectionUtils.findField(UserModel.class, (String) k);
//     field.setAccessible(true);
//     ReflectionUtils.setField(field, user, v);
// });

// System.out.println(editProfileRequest);
// userRepository.save(user);
// return user;