package com.bigidea.twitter.websockets.controllers;

import com.bigidea.twitter.classes.Chat.Chat;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    static List<User> users = new ArrayList<>();
    static List<Chat> chats = new ArrayList<>();
    private SimpMessagingTemplate template;
    private LogicHandler handler;

    @Autowired
    public UserController(SimpMessagingTemplate template, LogicHandler handler){
        this.template = template;
        this.handler = handler;
    }

    @MessageMapping("/user/{USER_ID}/follow/{FOLLOW_ID}")
    @SendToUser("/topic/user/follow/{USER_ID}")
    public void follow(@DestinationVariable int USER_ID, @DestinationVariable int FOLLOW_ID){

    }

    @MessageMapping("/user/{USER_ID}/unfollow/{FOLLOW_ID}")
    @SendToUser("/topic/user/unfollow/{USER_ID}")
    public void unfollow(@DestinationVariable int USER_ID, @DestinationVariable int FOLLOW_ID){

    }

    @MessageMapping("/user/{USER_ID}/chat/request/{USER2_ID}")
    @SendToUser("/topic/user/{USER_ID}/chat/start/{USER2_ID}")
    public void openChat(@DestinationVariable int USER_ID, @DestinationVariable int USER2_ID){

    }

    @MessageMapping("/user/chat/message/{CHAT_ID}")
    @SendTo("/topic/user/chat/{CHAT_ID}")
    public void message(@DestinationVariable int CHAT_ID){

    }

    @MessageMapping("/user/chat/close/{CHAT_ID}")
    public void closeChat(@DestinationVariable int CHAT_IF){

    }
}
