package com.bigidea.twitter.classes.User.interfaces;

import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.Chat.Chat;
import com.bigidea.twitter.classes.User.User;

public interface IUserManager {
    void follow(User user);
    void unFollow(User user);
    void addFollower(User user);
    void removeFollower(User user);
    User getUser();
}
