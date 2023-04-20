package com.example.demo.config;
import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.controller.AddNoServlet;


@Configuration
public class WebConfig {
   @Bean    
   public ServletRegistrationBean<HttpServlet> countryServlet() {
       ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
       servRegBean.setServlet(new AddNoServlet());
       servRegBean.addUrlMappings("/AddNoServlet");
       servRegBean.setLoadOnStartup(1);
       return servRegBean;
   }

}  