package com.pratiksha.socialfeed.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.repositories.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
    {
        UserModel foundedUser = userRepository.findByEmail(email);
        return UserPrincipal.create(foundedUser);
        // return new User(foundedUser.getEmail(), foundedUser.getPassword(), new ArrayList<>());
    }
}
