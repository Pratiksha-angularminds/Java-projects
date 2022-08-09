package com.pratiksha.socialfeed.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pratiksha.socialfeed.models.PostModel;
import com.pratiksha.socialfeed.payload.request.CommentPostRequest;
import com.pratiksha.socialfeed.repositories.PostRepository;
import com.pratiksha.socialfeed.services.FileService;
import com.pratiksha.socialfeed.services.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PostController 
{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    Map<String, Object> model = new HashMap<>();
       
   
    //-------------------------------------CREATE NEW POST-----------------------
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestParam Map<String,Object> reqBody , @RequestParam("postImg")MultipartFile files[]) throws IOException 
    {
  
        if(reqBody.get("caption")==null)
        {
            model.put("message", "Caption is required");
            return ResponseEntity.ok(model);
        }
        PostModel post = postService.createPost(reqBody,files);
        return ResponseEntity.ok(post);
        
    }


    //----------------------------------GET ALL POSTS-------------------------------------
    @GetMapping("/posts/getPosts")
    public ResponseEntity<?> getAllPosts(@RequestParam int page , @RequestParam int limit) 
    {
        Page<PostModel> posts = postService.getAllPosts(page,limit);
        if(!posts.isEmpty())
        {
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }
        else
        {
            model.put("message", "No posts are added yet!!");
            return new ResponseEntity<>(model,HttpStatus.OK);
        }

    }
    
    //----------------------------------GET POST BY ID------------------------------------
    @GetMapping("posts/getPostById/{postid}")
    public ResponseEntity<?> getPostById(@PathVariable("postid") String postid)
    {
       
        Optional<PostModel> post = postService.getPostById(postid);

        if(!post.isEmpty())
        {
            return new ResponseEntity<>(post,HttpStatus.OK);
        }
        else
        {
            model.put("message", "Post does not exists!!");
            return new ResponseEntity<>(model,HttpStatus.NOT_FOUND);
        }
    }


    //---------------------------------LIKE POST------------------------------------------
    @PutMapping("/posts/likes/{postid}")
    public ResponseEntity<?> likePost(@PathVariable("postid") String postid) 
    {
        PostModel post = postService.likePost(postid);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }


    //--------------------------------COMMENT POST----------------------------------------
    @PutMapping("/posts/comments/{postid}")
    public ResponseEntity<?> commentPost(@PathVariable("postid") String postid,@Valid @RequestBody CommentPostRequest reqBody) 
    {
       
        PostModel post = postService.commentPost(postid,reqBody);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    
}
