package com.pratiksha.socialfeed.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.repositories.UserRepository;

public class CustomAuditAware implements AuditorAware<String> 
{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public Optional<String> getCurrentAuditor() 
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) 
        {
            return null;
        }
        // System.out.println("------id:"+Optional.of(((UserModel) authentication.getPrincipal()).get_id()));
        UserModel user = userRepository.findByEmail(authentication.getName());
        return Optional.of(user.get_id());
    }
    
}
