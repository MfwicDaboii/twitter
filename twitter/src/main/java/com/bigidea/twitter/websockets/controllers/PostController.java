package com.bigidea.twitter.websockets.controllers;

import com.bigidea.twitter.classes.Posts.HashTag;
import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
     static List<User> users = UserController.users;
     static List<List<Post>> hashtagedPosts =new ArrayList<List<Post>>();
     private SimpMessagingTemplate template;
     private LogicHandler handler;

     @Autowired
     public PostController(SimpMessagingTemplate template, LogicHandler handler){
         this.template = template;
         this.handler = handler;
     }

     @MessageMapping("/post/tweet/{USER_ID}")
     @SendTo("/topic/post/activity/{USER_ID}")
     public void tweet(){

     }

     @MessageMapping("/post/retweet/{USER_ID}/{POST_ID}")
     @SendToUser("/topic/post/activity/{USER_ID}")
     public void reTweet(){

     }

     @MessageMapping("/post/comment/{USER_ID}/{POST_ID}")
     @SendToUser("/topic/post/activity/{USER_ID}")
     public void comment(){

     }

}
