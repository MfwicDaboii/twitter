package com.bigidea.twitter.websockets.controllers;

import com.bigidea.twitter.classes.Chat.Chat;
import com.bigidea.twitter.classes.Chat.Message;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.User.UserManager;
import com.bigidea.twitter.websockets.DTOs.ChatDTO;
import com.bigidea.twitter.websockets.DTOs.FollowDTO;
import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
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
    public FollowDTO follow(@DestinationVariable int USER_ID, @DestinationVariable int FOLLOW_ID, @Payload FollowDTO dto){
        handler.follow(USER_ID, FOLLOW_ID, dto.isFollow());
        User u1 = handler.getUser(USER_ID);
        User u2 = handler.getUser(FOLLOW_ID);

        template.convertAndSend("/topic/user/activity/{USER_ID}",new FollowDTO(u1.getFollowing()));
        template.convertAndSend("/topic/user/follow/{FOLLOW_ID}",new FollowDTO(u2.getFollowers()));

        return new FollowDTO(u1.getFollowing());
    }

    @MessageMapping("/user/{USER_ID}/chat/request/{USER2_ID}")
    @SendToUser("/topic/user/{USER_ID}/chat/start/{USER2_ID}")
    public ChatDTO openChat(@DestinationVariable int USER_ID, @DestinationVariable int USER2_ID, @Payload ChatDTO dto){
        ChatDTO DTO = handler.createChat(USER_ID);
        template.convertAndSend("/topic/user/chat/{USER_ID}",DTO);
        return DTO;
    }

    @MessageMapping("/user/chat/message/{CHAT_ID}")
    @SendTo("/topic/user/chat/{CHAT_ID}")
    public ChatDTO message(@DestinationVariable int CHAT_ID, @Payload Message msg){
        return  handler.sendMessage(CHAT_ID, msg);
    }

    @MessageMapping("/user/chat/close/{CHAT_ID}")
    public void closeChat(@DestinationVariable int CHAT_ID){
        handler.closeChat(CHAT_ID);
    }
}
