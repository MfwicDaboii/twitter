package com.bigidea.twitter.websockets.controllers;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.websockets.DTOs.LoginDTO;
import com.bigidea.twitter.websockets.DTOs.RegisterDTO;
import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

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
    public void cheat(){

    }
}
