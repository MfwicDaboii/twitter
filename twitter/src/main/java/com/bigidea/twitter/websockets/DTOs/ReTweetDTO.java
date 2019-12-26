package com.bigidea.twitter.websockets.DTOs;

public class ReTweetDTO {
    private int userID;
    private String content;



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
}
