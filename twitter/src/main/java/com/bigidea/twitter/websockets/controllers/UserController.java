package com.bigidea.twitter.websockets.controllers;

import com.bigidea.twitter.classes.Chat.Chat;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.websockets.DTOs.*;
import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private SimpMessagingTemplate template;
    private LogicHandler handler;

    @Autowired
    public UserController(SimpMessagingTemplate template, LogicHandler handler){
        this.template = template;
        this.handler = handler;
    }

    @MessageMapping("/user/{USER_ID}/follow/{FOLLOW_ID}")
    public void followRequest(@DestinationVariable int USER_ID, @DestinationVariable int FOLLOW_ID, @Payload FollowDTO dto){
        System.out.print("[SERVER] - Inside followrequest method!");
        String id = Integer.toString(FOLLOW_ID);
        User user = handler.getUser(USER_ID);
        dto.setUserID(user.getId());
        dto.setUsername(user.getFirstName());
        template.convertAndSend("/topic/user/request/follow/" + id, dto);
    }

    @MessageMapping("/user/{USER_ID}/answer/followRequest/{USER2_ID}")
    public void followRequestAnswer(@DestinationVariable int USER_ID, @DestinationVariable int USER2_ID, @Payload FollowDTO dto){
        handler.follow(USER2_ID, USER_ID);
        String id = Integer.toString(USER2_ID);
        User user = handler.getUser(USER_ID);
        dto.setUsername(user.getFirstName());
        template.convertAndSend("/topic/user/"+id+"/followRequestAnswer" , new FollowDTO(dto.isFollow()) );
        /*
        List<User> followers = handler.follow(USER2_ID, USER_ID);
        for(User u: followers){
            String id = Integer.toString(u.getId());
            template.convertAndSend("/topic/user/activity/" + id, new ActivityDTO() );
        }
        */
    }


    @MessageMapping("/user/{USER_ID}/chat/request/{USER2_ID}")
    @SendTo("/topic/user/chatRequest/{USER2_ID}")
    @SendToUser("/topic/user/{USER_ID}/chat")
    public ChatDTO openChat(@DestinationVariable int USER_ID, @DestinationVariable int USER2_ID, @Payload ChatDTO dto){
        return  handler.createChat(USER_ID);
    }

    @MessageMapping("/user/chat/{CHAT_ID}")
    @SendTo("/topic/user/chat/{CHAT_ID}")
    public ChatDTO message(@DestinationVariable int CHAT_ID, @Payload MsgDTO msg){
        return handler.sendMessage(CHAT_ID, msg);
    }

    @MessageMapping("/user/chat/close/{CHAT_ID}")
    public void closeChat(@DestinationVariable int CHAT_ID, @Payload FollowDTO dto){
        handler.closeChat(CHAT_ID);
    }

    @MessageMapping("/user/search")
    @SendToUser("/topic/user/search")
    public SearchDTO getUsersByName(String element){
            SearchDTO dto = new SearchDTO();
            dto.setResult(handler.getUsersByName(element));
            return dto;
    }

    @SubscribeMapping("/user/{USER_ID}/get/{USER2_ID}")
    public UserDTO getUser(@DestinationVariable int USER_ID, @DestinationVariable int USER2_ID){
        User user = handler.getUser(USER_ID);
        User other = handler.getUser(USER2_ID);
        boolean isFollowing = false;
        if(other.getFollowers().contains(user) || user.getFollowing().contains(other)){
            isFollowing = true;
        }
        return new UserDTO(other.getFirstName(), other.getLastName(),
                other.getAge(), other.getGender(), other.getBiography(),
                other.getFollowers(), other.getFollowing(),isFollowing );
    }
}
