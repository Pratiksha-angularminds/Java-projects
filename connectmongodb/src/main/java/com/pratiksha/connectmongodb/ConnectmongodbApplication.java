package com.pratiksha.connectmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pratiksha.connectmongodb.repo.StudentRepository;

@SpringBootApplication
public class ConnectmongodbApplication {
	@Autowired
	 StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(ConnectmongodbApplication.class, args);
		System.out.println("---------Started--------------");
	}

}
