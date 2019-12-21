package com.bigidea.twitter.classes.User;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.classes.User.interfaces.IUserManager;

import java.util.ArrayList;
import java.util.List;

public class UserManager implements IUserManager {
    static List<User> users = new ArrayList<>();

    public UserManager(){
    }

    public UserManager(List<User> list){
        users = list;
    }

    @Override
    public void follow(User user, User user2) {
        user.updateFollowing(user2, true);
    }

    @Override
    public void unFollow(User user, User user2) {
        user.updateFollowing(user2, false);
    }

    @Override
    public void addFollower(User user, User user2) {
       user.updateFollowers(user2,true);
    }

    @Override
    public void removeFollower(User user, User user2) {
        user.updateFollowers(user2,false);
    }

    @Override
    public User getUserById(int id){
        for(User u: users){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    public void addUserToList(User user){
        users.add(user);
    }
}
