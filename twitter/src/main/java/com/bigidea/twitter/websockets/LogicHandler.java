package com.bigidea.twitter.websockets;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.classes.Account.AccountManager;
import com.bigidea.twitter.classes.Chat.ChatManager;
import com.bigidea.twitter.classes.Posts.PostManager;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.User.UserManager;

import java.util.List;

public class LogicHandler {
    private static AccountManager accountManager = new AccountManager();
    private PostManager postManager;
    private ChatManager chatManager;
    private UserManager userManager;

    public boolean checkLogin(Account account){
        return accountManager.login(account.getUsername(), account.getPassword());
    }

    public void register(Account a, User u){
        int id = accountManager.register(a.getUsername(), a.getPassword());
        accountManager.addUser(id, u.getFirstName(),u.getLastName(), u.getAge(), u.getGender(), u.getBiography());
    }

}
