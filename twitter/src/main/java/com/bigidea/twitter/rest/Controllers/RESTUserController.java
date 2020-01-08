package com.bigidea.twitter.rest.Controllers;

import com.bigidea.twitter.rest.Entities.UserEntity;
import com.bigidea.twitter.rest.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class RESTUserController {

    private final UserRepository repository;

    @Autowired
    public RESTUserController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/getAll")
    public List<UserEntity> getAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public UserEntity getUserById(@PathVariable(value = "id") int id) {
        return repository.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody UserEntity entity) {
       // UserEntity entity =new UserEntity(dto.getFirstName(),dto.getFirstName(),dto.getAge(),dto.getGender(), dto.getBiography());
        repository.save(entity);
        return ResponseEntity.ok("resource created");
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@PathVariable(value = "id") int id, @RequestBody UserEntity entity) {
        repository.save(entity);
        return ResponseEntity.ok("resource saved");
    }

}
