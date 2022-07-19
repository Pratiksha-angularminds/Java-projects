package com.pratiksha.socialfeed.payload.request;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreatePostRequest 
{
    private String location;

    private MultipartFile postImg;

    @NotBlank
    private String caption;

    private String[] likes;

    // private List<CommentModel> comments;

    @CreatedBy
    private String createdBy;
}
