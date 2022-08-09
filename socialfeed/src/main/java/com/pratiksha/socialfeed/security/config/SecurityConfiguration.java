package com.pratiksha.socialfeed.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pratiksha.socialfeed.security.Jwt.JwtFilter;
import com.pratiksha.socialfeed.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends  WebSecurityConfigurerAdapter
{
    @Autowired
    private MyUserDetailsService myuserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception 
    {
        auth.userDetailsService(myuserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        // http
        // .csrf()
        // .disable()
        // .authorizeRequests()
        // .antMatchers( "/auth/register","/auth/login","/images/**")
        // .permitAll()
        // .anyRequest()
        // .authenticated()
        // .and()
        // .sessionManagement()
        // .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/auth/register","/auth/login","/images/**")
                .permitAll()
                .anyRequest()
                .authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception 
    {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuditorAware<String> auditorAware()
    {
        return new CustomAuditAware();
    }

    
}
