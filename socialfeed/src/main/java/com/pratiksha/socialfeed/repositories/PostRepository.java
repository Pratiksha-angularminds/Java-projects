package com.pratiksha.socialfeed.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pratiksha.socialfeed.models.PostModel;

public interface PostRepository extends MongoRepository<PostModel,String>
{
    // PostModel findById(String _id);
}
