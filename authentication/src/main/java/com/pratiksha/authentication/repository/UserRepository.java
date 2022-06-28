package com.pratiksha.authentication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pratiksha.authentication.models.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String> 
{
    UserModel findByEmail(String email);
}
