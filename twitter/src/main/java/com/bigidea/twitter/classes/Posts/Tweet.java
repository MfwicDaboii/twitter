package com.bigidea.twitter.classes.Posts;

import com.bigidea.twitter.enumerations.PostKind;

import java.util.ArrayList;

public class Tweet extends Post implements Comparable<Post> {
    private ArrayList<ReTweet> reTweets;
    private ArrayList<Comment> comments;

    public Tweet(){}

    public Tweet(String content, ArrayList<HashTag> HashTags) {
        super(content, PostKind.TWEET, HashTags);
        reTweets = new ArrayList<>();
        comments = new ArrayList<>();
    }
    public Tweet(int id,String content, ArrayList<HashTag> HashTags) {
        super(id,content, PostKind.TWEET, HashTags);
        reTweets = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public Tweet(int Id, String content, ArrayList<HashTag> HashTags, ArrayList<ReTweet> reTweets, ArrayList<Comment> comments) {
        super(Id, content, PostKind.TWEET, HashTags);
        this.reTweets = reTweets;
        this.comments = comments;
    }

    public ArrayList<ReTweet> getReTweets() {
        return reTweets;
    }

    public void setReTweets(ArrayList<ReTweet> reTweets) {
        this.reTweets = reTweets;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "reTweets=" + reTweets +
                ", comments=" + comments +
                '}';
    }

    @Override
    public int compareTo(Post o) {
        return 0;
    }
}
