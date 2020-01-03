package com.bigidea.twitter.rest.Controllers;

import com.bigidea.twitter.rest.Entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bigidea.twitter.rest.Repositories.AccountRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/account")
public class RESTAccountController {

    private final AccountRepository repository;

    @Autowired
    public RESTAccountController(AccountRepository repository){
        this.repository = repository;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody AccountEntity account) {
        System.out.println("[CONTROLLER] - Inside register!");
        repository.save(account);
    }

}
