package com.pratiksha.socialfeed.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.payload.request.EditProfileRequest;
import com.pratiksha.socialfeed.repositories.UserRepository;

@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private FileService fileService;
   
    //-------------------------EDIT PROFILE--------------------------
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
            String filePath = fileService.addFile(editProfileRequest.getProfileImg());
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