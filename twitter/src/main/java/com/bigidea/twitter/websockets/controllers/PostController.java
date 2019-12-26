package com.bigidea.twitter.websockets.controllers;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.websockets.DTOs.ReTweetDTO;
import com.bigidea.twitter.websockets.DTOs.TweetDTO;
import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {
     private SimpMessagingTemplate template;
     private LogicHandler handler;

     @Autowired
     public PostController(SimpMessagingTemplate template, LogicHandler handler){
         this.template = template;
         this.handler = handler;
     }

     @MessageMapping("/post/tweet/{USER_ID}")
     @SendTo("/topic/post/activity/{USER_ID}")
     public TweetDTO tweet(@DestinationVariable int USER_ID, @Payload TweetDTO dto){
          User user = handler.getUser(USER_ID);
          TweetDTO post = handler.createPost(USER_ID, dto.getContent());
          for (User u: user.getFollowers()) {
               template.convertAndSend("/topic/post/timeline/" + u.getId(), post);
          }
          return post;
     }

     @MessageMapping("/post/{POST_ID}/retweet/{USER_ID}")
     @SendTo("/topic/post/activity/{USER_ID}")
     public void reTweet(@DestinationVariable int POST_ID,@DestinationVariable int USER_ID, ReTweetDTO dto){
          ReTweetDTO post = handler.createRetweet();
     }

     @MessageMapping("/post/{POST_ID}/comment/{USER_ID}")
     @SendTo("/topic/post/activity/{USER_ID}")
     public void comment(){

     }

}
