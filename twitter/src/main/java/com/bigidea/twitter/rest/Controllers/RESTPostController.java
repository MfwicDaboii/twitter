package com.bigidea.twitter.rest.Controllers;

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

    @GetMapping("/get/timeline")
    public List<PostEntity>  getTimeLine(@Valid @RequestBody int id){
        List<PostEntity> timeline = new ArrayList<>();
        List<Integer> ids = followRepository.getAllByUserID(id);
        for (int user_id: ids) {
            timeline.addAll(postRepository.getActivityByUserID(user_id, Sort.by(Sort.Direction.ASC, "date")));
        }
        //sort posts on date and kind
        return  timeline;
    }

    @GetMapping("/get/activity")
    public List<PostEntity> getActivity(@Valid @RequestBody int id){
        return postRepository.getActivityByUserID(id,Sort.by(Sort.Direction.ASC, "date"));
    }

    @GetMapping
    public PostEntity getPostById(@Valid @RequestBody int id){
        return postRepository.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody PostEntity post){
        post.setDatum(LocalDate.now().toString());
        postRepository.save(post);
    }


}
