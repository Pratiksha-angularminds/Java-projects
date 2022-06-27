package com.pratiksha.connectmongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;

import com.pratiksha.connectmongodb.models.Student;

public interface StudentRepository extends MongoRepository<Student,String> 
{
   
}
