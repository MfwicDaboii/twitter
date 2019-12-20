package com.bigidea.twitter.classes.User;

import com.bigidea.twitter.classes.User.interfaces.IUserManager;

public class UserManager implements IUserManager {
    private User user;

    public UserManager(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void follow(User user) {
        this.user.updateFollowing(user, true);
    }

    @Override
    public void unFollow(User user) {
        this.user.updateFollowing(user, false);
    }

    @Override
    public void addFollower(User user){
        this.user.updateFollowers(user,true);
    }

    @Override
    public void removeFollower(User user){
        this.user.updateFollowers(user,false);
    }
}
