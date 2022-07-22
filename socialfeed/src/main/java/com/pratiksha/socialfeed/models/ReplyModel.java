package com.pratiksha.socialfeed.models;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class ReplyModel 
{
    @Id
    private String _id;
    private String comment;
    
    private ArrayList<String> likes = new ArrayList<>();

    @CreatedBy
    private String repliedBy;

    @CreatedDate
    private Date repliedAt;
}
