package com.pratiksha.authentication.config;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebConfig {
   @Bean    
   public ServletRegistrationBean<HttpServlet> countryServlet() {
       ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
       servRegBean.setServlet(new HelloCountryServlet());
       servRegBean.addUrlMappings("/country/*");
       servRegBean.setLoadOnStartup(1);
       return servRegBean;
   }

}  