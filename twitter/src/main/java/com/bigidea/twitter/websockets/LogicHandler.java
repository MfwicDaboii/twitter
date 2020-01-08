package com.bigidea.twitter.websockets;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.classes.Account.AccountManager;
import com.bigidea.twitter.classes.Algorithm;
import com.bigidea.twitter.classes.Chat.ChatManager;
import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.Posts.PostManager;
import com.bigidea.twitter.classes.Posts.ReTweet;
import com.bigidea.twitter.classes.Posts.Tweet;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.User.UserManager;
import com.bigidea.twitter.websockets.DTOs.*;

import java.util.ArrayList;
import java.util.List;

public class LogicHandler {
    private static AccountManager accountManager = new AccountManager();
    private UserManager userManager = new UserManager();
    private PostManager postManager = new PostManager();
    private ChatManager chatManager = new ChatManager();

    public LoginDTO checkLogin(Account account){
        int result = accountManager.login(account.getUsername(), account.getPassword());
        if(result > 0){
            return new LoginDTO(true, result);
        }
        return new LoginDTO(false, result);
    }

    public LoginDTO register(Account a, User u){
        int id = accountManager.register(a.getUsername(), a.getPassword());
        Account account = accountManager.getAccountById(id);
        int position = userManager.getPosition(u.getFirstName());

        accountManager.addUser(id, u.getFirstName(),u.getLastName(), u.getAge(), u.getGender(), u.getBiography());
        userManager.addUserToList(position,account.getUser());

        return new LoginDTO(true, id);
    }

    public List<User> follow(int u1, int u2){
        User main = userManager.getUserById(u1);
        User other = userManager.getUserById(u2);
        System.out.println("[HANDLER] - Inside follow method");
        if(main.getFollowing().contains(other) || other.getFollowers().contains(main)){
            System.out.println("[HANDLER] - u1 was already following u2. Unfollowing now");
            userManager.unFollow(main,other);
            userManager.removeFollower(other, main);
        }else{
            System.out.println("[HANDLER] - u1 is now following u2");
            userManager.follow(main,other);
            userManager.addFollower(other, main);
            return main.getFollowers();
        }
        return new ArrayList<>();
    }

    public User getUser(int id){
       return userManager.getUserById(id);
    }

    public ChatDTO createChat(int id){
        User user = getUser(id);
        System.out.println("[HANDLER] - Got user " + user.getFirstName());
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        return chatManager.create(users);
    }

    public ChatDTO sendMessage(int id, MsgDTO msg){
        User user = getUser(msg.getId());
        return new ChatDTO(chatManager.sendMessage(id,user,msg.getContent()));
    }
    
    public void closeChat(int id){
        chatManager.closeChat(id);
    }

    public List<User>getUsersByName(String element){
        int position = userManager.getPosition(element);
        return userManager.getUserByName(position, element);
    }

    public TweetDTO createPost(int id, String content){
        User user = userManager.getUserById(id);
        Post post = postManager.postTweet(user,content);
        return new TweetDTO(post.getId(),id, post.getContent(),user.getFirstName(), post.getDate().toString());
    }

    public ReTweetDTO createReTweet(int postid, int originalUserId, int rtUserId,String content){
        User OUser = userManager.getUserById(originalUserId);
        User RTUser = userManager.getUserById(rtUserId);
        Tweet orginal = postManager.getPostById(OUser, postid) ;
        Post rt = postManager.postReTweet(RTUser, content, orginal);
        return new ReTweetDTO(rt.getId(),rt.getContent(),RTUser.getFirstName()
                ,rt.getDate().toString() ,orginal.getContent(), OUser.getFirstName());
    }

    public List<Post> getAllActivity(int id){
        User user = userManager.getUserById(id);
        Algorithm algorithm =  new Algorithm();
        List<Post> sortedList = new ArrayList<>();
        for(User u:user.getFollowing()){
            sortedList = algorithm.sortPostsByDate(sortedList, u.getTimeline());
        }
        return  sortedList;
    }
}
