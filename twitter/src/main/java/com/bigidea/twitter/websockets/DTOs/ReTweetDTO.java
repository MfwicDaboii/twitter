package com.bigidea.twitter.websockets.DTOs;

import com.bigidea.twitter.classes.Posts.Post;

public class ReTweetDTO {
    private int postID;
    private int userID;
    private String content;
    private String name;
    private String datum;
    private String oldName;
    private String oldContent;

    public ReTweetDTO(){}

    public ReTweetDTO(int postID,String content, String name, String datum, String oldContent, String oldName) {
        this.postID = postID;
        this.content = content;
        this.name = name;
        this.datum = datum;
        this.oldContent = oldContent;
        this.oldName = oldName;
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

    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
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
