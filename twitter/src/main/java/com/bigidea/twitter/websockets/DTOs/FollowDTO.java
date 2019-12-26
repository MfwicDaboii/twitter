package com.bigidea.twitter.websockets.DTOs;

import com.bigidea.twitter.classes.User.User;

import java.util.ArrayList;

public class FollowDTO {
    private int userID;
    private int followerID;
    private String username;
    private boolean follow;

    public FollowDTO(){}

    public FollowDTO(boolean follow) {
        this.follow = follow;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFollowerID() {
        return followerID;
    }

    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}
