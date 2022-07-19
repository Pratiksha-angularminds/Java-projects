package com.pratiksha.socialfeed.models;

import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Document("Posts")
@Data
public class PostModel 
{
    @Id
    private String _id;

    private String location;

    private MultipartFile postImg;

    // @NotBlank
    private String caption;

    private String[] likes;

    private List<CommentModel> comments[];

    @CreatedBy
    private String createdBy;
}
