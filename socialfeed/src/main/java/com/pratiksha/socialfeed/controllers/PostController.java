package com.pratiksha.socialfeed.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pratiksha.socialfeed.models.PostModel;
import com.pratiksha.socialfeed.payload.request.CreatePostRequest;
import com.pratiksha.socialfeed.repositories.PostRepository;
import com.pratiksha.socialfeed.repositories.UserRepository;
import com.pratiksha.socialfeed.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController 
{
    @Autowired
    private PostRepository postRepository;

    // @Autowired
    // private UserService userService;

    //--------------------------------------create new post----------------------
    @PostMapping("/posts")
    public ResponseEntity<?> createPost( CreatePostRequest req) throws IOException 
    {
        // String file = userService.addFile(entity.getPostImg());
        // PostModel post = new PostModel();
        // post.setCaption(entity.getCaption());
        // System.out.println("---------"+entity);
        PostModel saved = new PostModel();
        saved.setCaption(req.getCaption());
        postRepository.save(saved);
        return ResponseEntity.ok(saved);
    }


    
}
