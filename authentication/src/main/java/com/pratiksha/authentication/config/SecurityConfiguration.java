package com.pratiksha.authentication.config;



import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.pratiksha.authentication.services.JwtFilterRequest;
import com.pratiksha.authentication.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Configuration
@EnableWebSecurity
@ComponentScan
// @EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    private String SECRET_KEY = "secret";

    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

   
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception 
    {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
       
        http
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers( "/register","/login","/forgot-password","/country")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        // .oauth2Login()
        // .successHandler(new AuthenticationSuccessHandler() 
        // {
        //     @Override
        //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        //             Authentication authentication) throws IOException, ServletException 
        //             {
                        
        //                 DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
        //                 String email = oauthUser.getAttribute("email");

        //                 String token = Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
        //                 .signWith(SignatureAlgorithm.HS512,SECRET_KEY).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).compact();

        //                 System.out.println("-----------------------------------token:-----"+token);
        //                 userService.processOAuthPostLogin(email);
        //             }
        // })
        // .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        // http
        // .csrf()
        // .disable()
        // .authorizeRequests()
        // .antMatchers("/login")
        // .permitAll()
        // .anyRequest()
        // .authenticated();
    

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
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

    // @Override

    // public void configureViewResolvers(ViewResolverRegistry registry) {
    //     InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    //     resolver.setPrefix("/WEB-INF/view/");
    //     resolver.setSuffix(".jsp");
    //     resolver.setViewClass(JstlView.class);
    //     registry.viewResolver(resolver);
    // }
}
