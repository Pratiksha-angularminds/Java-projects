package com.pratiksha.socialfeed.payload.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EditProfileImgRequest 
{
    private MultipartFile profileImg;
    private Boolean removeImg = false;

}
