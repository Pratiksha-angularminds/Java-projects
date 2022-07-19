package com.pratiksha.socialfeed.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pratiksha.socialfeed.models.UserModel;

public interface UserRepository extends MongoRepository<UserModel,String>
{
    Boolean existsByEmail(String email);
    UserModel findByEmail(String email);

}
