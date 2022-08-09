package com.pratiksha.socialfeed.services;

import java.io.IOException;
import java.lang.reflect.Field;
import java.rmi.server.ObjID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pratiksha.socialfeed.models.CommentModel;
import com.pratiksha.socialfeed.models.FileModel;
import com.pratiksha.socialfeed.models.PostModel;
import com.pratiksha.socialfeed.models.UserModel;
import com.pratiksha.socialfeed.payload.request.CommentPostRequest;
import com.pratiksha.socialfeed.repositories.PostRepository;
import com.pratiksha.socialfeed.repositories.UserRepository;

@Service
public class PostService 
{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserRepository userRepository;

    
    
    //-----------------------------------CREATE NEW POST----------------------------------
    public PostModel createPost(Map<String,Object> reqBody , MultipartFile files[]) throws IOException
    {
        PostModel newPost = new PostModel();

        reqBody.forEach((k,v)->
        {
            Field field = ReflectionUtils.findField(PostModel.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, newPost, v);
        });

        List<FileModel> fileModel = fileService.addMultipleFile(files);

        newPost.setPostImg(fileModel);
        postRepository.save(newPost);

        return newPost;
    }

    //-----------------------------------SERVICE TO GET ALL POSTS----------------------------
    public Page<PostModel> getAllPosts(int page , int limit)
    {
        Page<PostModel> posts = postRepository.findAll(PageRequest.of(page, limit));
        return posts;
    }

    //-----------------------------------SERVICE TO GET POST BY ID----------------------------
    public Optional<PostModel> getPostById(String postid)
    {
        Optional<PostModel> post = postRepository.findById(postid);
        return post;
    }
    

    //-----------------------------------SERVICE TO LIKE POST----------------------------
    public PostModel likePost(String postid)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel CurrentUser = userRepository.findByEmail(email);

        PostModel post = postRepository.findById(postid).orElse(null);
        ArrayList<String> likeArr = post.getLikes(); 
   
        if(likeArr.size() == 0)
        {
            likeArr.add(CurrentUser.get_id());
        }
        else
        {
            if(!likeArr.contains(CurrentUser.get_id()))
            {
                likeArr.add(CurrentUser.get_id());
            }
            else
            {
               likeArr.remove(CurrentUser.get_id());
            }
        }
        
        post.setLikes(likeArr);
        postRepository.save(post);       
        return post;
    }


    //----------------------------------SERVICE TO COMMENT POST---------------------------
    public PostModel commentPost(String postid,CommentPostRequest reqBody)
    {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel CurrentUser = userRepository.findByEmail(email);

        PostModel post = postRepository.findById(postid).orElse(null);
        CommentModel comment = new CommentModel();
        ArrayList<CommentModel> commentsArr = post.getComments();

        // comment.set_id(ObjectId.get().toString());   ---------for setting id------
        comment.setComment(reqBody.getComment());
        comment.setCreatedBy(CurrentUser.get_id());
        comment.setCreatedAt(new Date());

        commentsArr.add(comment);
        post.setComments(commentsArr);

        System.out.println(comment.get_id());
        postRepository.save(post);
        return post;
    }
}

