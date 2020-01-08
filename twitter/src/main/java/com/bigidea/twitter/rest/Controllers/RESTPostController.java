package com.bigidea.twitter.rest.Controllers;

import com.bigidea.twitter.rest.Entities.FollowEntity;
import com.bigidea.twitter.rest.Entities.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bigidea.twitter.rest.Repositories.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class RESTPostController {
    private final PostRepository postRepository;
    private final FollowRepository followRepository;

    @Autowired
    public RESTPostController(PostRepository repository, FollowRepository followRepository){
        this.postRepository = repository;
        this.followRepository = followRepository;
    }

    @GetMapping("/get/all")
    public List<PostEntity> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/get/timeline/{user_id}")
    public List<PostEntity> getTimeLine(@PathVariable(value = "user_id") int id){
        List<PostEntity> timeline = new ArrayList<>();
        List<FollowEntity> following = followRepository.getAllFollowers(id);
        for(FollowEntity entity: following){
            timeline.addAll(postRepository.getActivityByUserID(entity.getFollowerID()));
        }
        //sort posts on date and kind
        return  timeline;
    }

    @GetMapping("/get/activity/{user_id}")
    public List<PostEntity> getActivity(@PathVariable(value = "user_id") int id){
        return postRepository.getActivityByUserID(id);
    }

    @GetMapping("/get/{post_id}")
    public PostEntity getPostById(@PathVariable(value = "post_id") int id){
        return postRepository.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody PostEntity post){
        post.setDate(LocalDate.now().toString());
        postRepository.save(post);
    }
}
