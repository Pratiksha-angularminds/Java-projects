package com.pratiksha.socialfeed.payload.request;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class CommentPostRequest 
{
    // @Id
    // private String _id;
    
    @NotBlank(message = "Plz add the comment text..")
    private String comment;    
}
