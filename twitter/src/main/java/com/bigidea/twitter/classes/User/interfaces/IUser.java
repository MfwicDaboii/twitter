package com.bigidea.twitter.classes.User.interfaces;

import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.User.User;

import java.util.ArrayList;

public interface IUser {
    void updateTimeline(Post post);
    void updateFollowers(User user, boolean follow);
    void updateFollowing(User user, boolean follow);
    String getFirstName();
    int getId();
}
