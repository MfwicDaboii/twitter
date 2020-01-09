package com.bigidea.twitter.rest.Controllers;

import com.bigidea.twitter.rest.Entities.HashTagEntity;
import com.bigidea.twitter.rest.Entities.PostEntity;
import com.bigidea.twitter.rest.Repositories.HashTagRepository;
import com.bigidea.twitter.rest.Specifications.HashTagSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class RESTHashTagController {

    private final HashTagRepository repository;

    @Autowired
    public RESTHashTagController(HashTagRepository repository){
        this.repository = repository;
    }

    @GetMapping("/get/post/{tag}")
    public HashTagEntity getPostsByName(@Valid @RequestBody String name){
        return repository.getHashTagEntityByName(name);
    }

    //TODO: algorithm so its alphabetical or use query
    @GetMapping("/get/all")
    public List<HashTagEntity> getAll(){
        return repository.findAll();
    }

    @GetMapping("/search/{element}")
    public List<HashTagEntity> getAllByName(@PathVariable(value = "element") String searchElement){
        return repository.findAll(Specification.where(HashTagSpecifications.nameLike(searchElement)));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody HashTagEntity entity) {
        repository.save(entity);
        return ResponseEntity.ok("Created new tag");
    }
}
