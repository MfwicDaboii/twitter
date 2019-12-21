package com.bigidea.twitter.websockets.DTOs;

import com.bigidea.twitter.classes.User.User;

import java.util.ArrayList;

public class FollowDTO {
    private boolean follow;
    private ArrayList<User> users;

    public FollowDTO(){}

    public FollowDTO(ArrayList<User> users) {
        this.users = users;
    }

    public FollowDTO(boolean follow) {
        this.follow = follow;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
