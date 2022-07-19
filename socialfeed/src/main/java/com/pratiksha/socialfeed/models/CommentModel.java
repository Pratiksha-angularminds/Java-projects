package com.pratiksha.socialfeed.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class CommentModel 
{
    @Id
    private String _id;
    private String comment;

    private String[] likes;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdAt;
}
