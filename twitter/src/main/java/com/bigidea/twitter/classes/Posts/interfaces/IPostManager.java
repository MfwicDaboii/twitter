package com.bigidea.twitter.classes.Posts.interfaces;

import com.bigidea.twitter.classes.Hashtag.HashTag;
import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.Posts.Tweet;
import com.bigidea.twitter.classes.User.User;

import java.util.ArrayList;

public interface IPostManager {
    Post postTweet(User user, String content);
    Post postReTweet(User user,String content, Tweet originalTweet);
    Post postComment(User user,String content, Post post);
    ArrayList<String> splitContent(String content);
    ArrayList<HashTag> createTags(ArrayList<String> topics);
    void like(int userId,Post post ,boolean isLike);
}
