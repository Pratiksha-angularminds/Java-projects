package com.pratiksha.authentication.services;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;



@Service
public class EmailService 
{
    
    @Autowired private JavaMailSender javaMailSender;
 
    @Value("${spring.mail.username}") private String sender;
 
    // Method 1
    // To send a simple email
    public String sendMail(String recipientEmail, String link)
    {

        try {
 
           
            // Creating a mime message
            MimeMessage message = javaMailSender.createMimeMessage();   
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
            // Setting up necessary details
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(recipientEmail);
          
            String subject = "Forgot Password";
     
            String text = "<p>Hello,</p>"
                            + "<p>You have requested to reset your password.</p>"
                            + "<p>Click the link below to change your password:</p>"
                            // + "<p><a href=\"" + link + "\">Change my password</a></p>"
                            + "<p style='color:#1890FF'> " + link +"</p>"
                            + "<br>"
                            + "<p>Ignore this email if you do remember your password, "
                            + "or you have not made the request.</p>";
 
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text,true);
            

            // Sending the mail
            javaMailSender.send(message);
            return "true";
            
        }
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
