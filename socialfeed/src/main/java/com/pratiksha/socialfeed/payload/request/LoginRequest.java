package com.pratiksha.socialfeed.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginRequest 
{
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4,message = "password must be of atleast 4 character")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$",message = "Password must contain one number and one special character")
    private String password;    
}
