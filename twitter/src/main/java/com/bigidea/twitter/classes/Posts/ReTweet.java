package com.bigidea.twitter.classes.Posts;

import com.bigidea.twitter.classes.Hashtag.HashTag;
import com.bigidea.twitter.enumerations.PostKind;

import java.util.ArrayList;

public class ReTweet extends Post{
    private Tweet originalTweet;
    private ArrayList<Comment> comments;

    public ReTweet(){}

    public ReTweet(String content, Tweet originalTweet, ArrayList<HashTag> HashTags) {
        super(content, PostKind.RETWEET, HashTags);
        this.originalTweet = originalTweet;
    }

    public ReTweet(int Id, String content, Tweet originalTweet, ArrayList<HashTag> HashTags) {
        super(Id, content, PostKind.RETWEET, HashTags);
        this.originalTweet = originalTweet;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Tweet getOriginalTweet() {
        return originalTweet;
    }

    public void setOriginalTweet(Tweet originalTweet) {
        this.originalTweet = originalTweet;
    }
}
