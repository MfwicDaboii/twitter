package com.bigidea.twitter.classes.Posts.interfaces;

import com.bigidea.twitter.classes.Posts.HashTag;
import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.Posts.Tweet;

import java.util.ArrayList;

public interface IPostManager {
    Post postTweet(String content);
    Post postReTweet(String content, Tweet originalTweet);
    Post postComment(String content, Post post);
    ArrayList<String> splitContent(String content);
    ArrayList<HashTag> createTags(ArrayList<String> topics);
    void like(int userId,Post post ,boolean isLike);
}
