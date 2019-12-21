package com.bigidea.twitter.websockets.controllers;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.websockets.DTOs.LoginDTO;
import com.bigidea.twitter.websockets.DTOs.RegisterDTO;
import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {
    private LogicHandler handler;

    @Autowired
    public AccountController( LogicHandler handler){
        this.handler = handler;
    }

    @MessageMapping("/login")
    @SendToUser("/topic/account")
    public LoginDTO login(@Payload LoginDTO account, SimpMessageHeaderAccessor accessor){
        //TODO: haal user op na inloggen (*RESTAPI)
        boolean result = handler.checkLogin(new Account(account.getUsername(), account.getPassword()));
        return new LoginDTO(result);
    }

    @MessageMapping("/register")
    @SendToUser("/topic/account")
    public RegisterDTO register(@Payload RegisterDTO dto){
        handler.register(dto.getAccount(), dto.getUser());
        return new RegisterDTO(true);
    }

    @MessageMapping("/login/hack")
    @SendToUser("/topic/account")
    public RegisterDTO cheat(@Payload LoginDTO account){
        handler.register(
                new Account("Bot", "Wolfpower123!"),
                new User("Joshua", "Keulers",21, Gender.MALE, "Coole boy")
        );
        return new RegisterDTO(true);
    }
}
