package com.bigidea.twitter.websockets;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.classes.Account.AccountManager;
import com.bigidea.twitter.classes.Chat.ChatManager;
import com.bigidea.twitter.classes.Posts.PostManager;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.User.UserManager;
import com.bigidea.twitter.websockets.DTOs.ChatDTO;
import com.bigidea.twitter.websockets.DTOs.LoginDTO;
import com.bigidea.twitter.websockets.DTOs.MsgDTO;

import java.util.ArrayList;
import java.util.List;

public class LogicHandler {
    private static AccountManager accountManager = new AccountManager();
    private UserManager userManager = new UserManager();
    private PostManager postManager;
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

    public void follow(int u1, int u2, boolean follow){
        User main = userManager.getUserById(u1);
        User other = userManager.getUserById(u2);
        if(follow){
            userManager.follow(main,other);
            userManager.addFollower(other, main);
        }else{
            userManager.unFollow(main,other);
            userManager.removeFollower(other, main);
        }
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
}
