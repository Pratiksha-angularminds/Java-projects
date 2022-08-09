package com.pratiksha.socialfeed.payload.request;
import java.util.Date;
import javax.validation.constraints.Email;
import lombok.Data;

@Data
public class EditProfileRequest 
{
    private String name;
    
    @Email
    private String email;

    private String gender;
    private Date dob;

    
    private String mobile;
   
    private Boolean isEmailVerified = false;
}
