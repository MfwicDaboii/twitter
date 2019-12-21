package com.bigidea.twitter.websockets.DTOs;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.classes.User.User;

public class RegisterDTO {
    private Account account;
    private User user;
    private boolean state;

    public RegisterDTO(){}

    public RegisterDTO(boolean state){
        this.state = state;
    }

    public RegisterDTO(Account account, User user) {
        this.account = account;
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
