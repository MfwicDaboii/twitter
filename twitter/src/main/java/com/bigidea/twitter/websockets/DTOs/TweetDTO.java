package com.bigidea.twitter.websockets.DTOs;

public class TweetDTO {
    private int postID;
    private int userID;
    private String content;
    private String name;
    private String datum;

    public TweetDTO(){}

    public TweetDTO(String content){
        this.content = content;
    }

    public TweetDTO(int postID, int userID, String content, String name, String datum) {
        this.postID = postID;
        this.userID = userID;
        this.content = content;
        this.name = name;
        this.datum = datum;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
