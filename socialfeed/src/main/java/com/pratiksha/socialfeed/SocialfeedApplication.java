package com.pratiksha.socialfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
// import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoAuditing
// @EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SocialfeedApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SocialfeedApplication.class, args);
		System.out.println("--------------started------------------");
	}

}
