package com.bigidea.twitter.rest.Controllers;

import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.rest.Entities.HashTagEntity;
import com.bigidea.twitter.rest.Entities.Hashtags_PostsEntity;
import com.bigidea.twitter.rest.Entities.PostEntity;
import com.bigidea.twitter.rest.Repositories.Hashtags_PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tag/post")
public class RESTHashTagsPostController {

    private final Hashtags_PostsRepository repository;
    private final RESTHashTagController tagController;
    private final RESTPostController postController;


    @Autowired
    public RESTHashTagsPostController(Hashtags_PostsRepository repository, RESTHashTagController tagController, RESTPostController postController){
        this.repository = repository;
        this.tagController = tagController;
        this.postController = postController;
    }

    @GetMapping("/get/{tag}")
    public List<PostEntity> getAllPostsByTagName(@PathVariable(value = "tag") String name){
        HashTagEntity tag = tagController.getPostsByName(name);
        System.out.println(tag.getId());
        if(tag.equals(null)){
            return new ArrayList<PostEntity>();
        }
        List<Integer> post_ids = repository.getAllPostIdsByTagId(tag.getId());
        System.out.println(post_ids);
        List<PostEntity> posts = new ArrayList<>();
        for(int id: post_ids){
            posts.add(postController.getPostById(id));
        }
        return  posts;
    }

    @GetMapping("/get/all")
    public List<Hashtags_PostsEntity> getAll(){
        return repository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTagToPost(@Valid @RequestBody Hashtags_PostsEntity entity){
        repository.save(entity);
        return ResponseEntity.ok("created new link!");
    }
}
