package com.bigidea.twitter.classes.User.interfaces;

import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.Chat.Chat;
import com.bigidea.twitter.classes.User.User;

public interface IUserManager {
    void follow(User user,User user2);
    void unFollow(User user,User user2);
    void addFollower(User user,User user2);
    void removeFollower(User user,User user2);
    User getUserById(int id);
}
