package com.bigidea.twitter.rest.Controllers;

import com.bigidea.twitter.rest.Entities.FollowEntity;
import com.bigidea.twitter.rest.Repositories.FollowRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class RESTFollowController {

    private final FollowRepository repository;

    @Autowired
    public RESTFollowController(FollowRepository repository){
        this.repository = repository;
    }

    @GetMapping(value = "/followers/{id}")
    public List<Integer> getFollowers(@PathVariable(value = "id") int id){
        List<FollowEntity> followers = repository.getAllFollowers(id);
        List<Integer> list = new ArrayList<>();
        for(FollowEntity entity: followers){
            list.add(entity.getFollowerID());
        }
        return list;
    }

    @GetMapping(value = "/following/{id}")
    public List<Integer> getFollowing(@PathVariable(value = "id") int id){
        List<FollowEntity> following = repository.getAllFollowing(id);
        List<Integer> list = new ArrayList<>();
        for(FollowEntity entity: following){
            list.add(entity.getUserID());
        }
        return list;
    }

    @PostMapping(value = "/{id}/{user_id}")
    public ResponseEntity<?> follow(@PathVariable(value = "id") int id,@PathVariable(value = "user_id") int user_id){
        repository.save(new FollowEntity(id,user_id));
        return ResponseEntity.ok("resource created");
    }

    @DeleteMapping(value="/unfollow/{id}/{user_id}")
    public ResponseEntity<?> unfollow(@PathVariable(value = "id") int id,@PathVariable(value = "user_id") int user_id){
        repository.unfollow(id,user_id);
       return ResponseEntity.ok("unfollowed user");
    }
}
