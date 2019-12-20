package com.bigidea.twitter.classes.Account;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.Account.interfaces.IAccount;

import javax.persistence.*;
import java.security.SecureRandom;


public class Account implements IAccount {
    private int id;
    private String username;
    private String password;
    private User user;
    private static Account instance = null;

    public Account(){

    }
    public Account(String username, String password){
        this.username = username;
        this.password = password;
        user = null;

        SecureRandom rnd = new SecureRandom();
        id = rnd.nextInt(9999);
    }
    public Account(String username, String password, User user){
        this.username = username;
        this.password = password;
        this.user = user;

        SecureRandom rnd = new SecureRandom();
        id = rnd.nextInt(9999);
    }

    public static void setInstance(Account account){
            instance = account;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void updateCredentials(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
