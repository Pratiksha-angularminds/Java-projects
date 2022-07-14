package com.pratiksha.socialfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SocialfeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialfeedApplication.class, args);
		System.out.println("--------------started------------------");
	}

}
