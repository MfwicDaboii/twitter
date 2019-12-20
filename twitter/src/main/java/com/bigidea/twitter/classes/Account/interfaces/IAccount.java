package com.bigidea.twitter.classes.Account.interfaces;

import com.bigidea.twitter.classes.User.User;

public interface IAccount {
    String getUsername();
    String getPassword();
    User getUser();
    void setUser(User user);
    void updateCredentials(String username, String password);
}
