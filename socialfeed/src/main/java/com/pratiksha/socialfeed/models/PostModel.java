package com.pratiksha.socialfeed.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Document("Posts")
@Data
public class PostModel 
{
    @Id
    private String _id;

    private String location = "";

    private List<FileModel> postImg = new ArrayList<>();

    @NotBlank
    private String caption;

    private ArrayList<String> likes = new ArrayList<>();

    private ArrayList<CommentModel> comments = new ArrayList<>();

    private String[] bookmarks = new String[0];

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
