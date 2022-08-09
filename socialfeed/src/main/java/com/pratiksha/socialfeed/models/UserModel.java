package com.pratiksha.socialfeed.models;



import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document("Users")
@Data

public class UserModel 
{
    @Id
    private String _id;

    @NotNull
    // @Size(max=1,message = "not allowed")
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    private String gender;
    private Date dob;
    private String mobile;
    private String profileImg;
    private Boolean removeImg = false;

    private Boolean isEmailVerified = false;
    
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public String get_id() {
        return _id;
    }
    
}
