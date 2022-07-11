package com.pratiksha.socialfeed.models;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("Users")
@Data
public class UserModel 
{
    @Id
    private String _id;
    private String name;
    private String email;
    private String password;


    private String gender;
    private Date dob;
    private String mobile;
    private Boolean isEmailVerified = false;

    
}
