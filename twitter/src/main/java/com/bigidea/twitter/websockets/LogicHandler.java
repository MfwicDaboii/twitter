package com.bigidea.twitter.websockets;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.classes.Account.AccountManager;
import com.bigidea.twitter.classes.Chat.ChatManager;
import com.bigidea.twitter.classes.Chat.Message;
import com.bigidea.twitter.classes.Posts.PostManager;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.User.UserManager;
import com.bigidea.twitter.websockets.DTOs.ChatDTO;
import com.bigidea.twitter.websockets.DTOs.LoginDTO;
import com.bigidea.twitter.websockets.DTOs.RegisterDTO;

import java.util.ArrayList;

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
        accountManager.addUser(id, u.getFirstName(),u.getLastName(), u.getAge(), u.getGender(), u.getBiography());
        Account account = accountManager.getAccountById(id);
        userManager.addUserToList(account.getUser());
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
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        return chatManager.create(users);
    }

    public ChatDTO sendMessage(int id, Message msg){
        User user = getUser(msg.getUser().getId());
        return new ChatDTO(chatManager.sendMessage(id,user,msg.getContent()));
    }

    public void closeChat(int id){
        chatManager.closeChat(id);
    }
}
