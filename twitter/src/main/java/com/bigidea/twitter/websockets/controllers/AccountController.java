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
        return handler.checkLogin(new Account(account.getUsername(), account.getPassword()));
    }

    @MessageMapping("/register")
    @SendToUser("/topic/account")
    public LoginDTO register(@Payload RegisterDTO dto){
        return handler.register(dto.getAccount(), dto.getUser());
    }

    @MessageMapping("/login/hack")
    @SendToUser("/topic/account")
    public LoginDTO cheat(@Payload LoginDTO account){
        return handler.register(
                new Account("Bot", "Wolfpower123!"),
                new User("Joshua", "Keulers",21, Gender.MALE, "Coole boy")
        );
    }
}
