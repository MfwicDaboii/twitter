package com.bigidea.twitter.rest.Entities;

import com.bigidea.twitter.enumerations.PostKind;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class PostEntity {
    private int id;
    private int userID;
    private String content;
    private String date;
    private PostKind kind;
    private int originalPostID;

    public PostEntity(){}

    public PostEntity(int userID, String content, PostKind kind) {
        this.userID = userID;
        this.content = content;
        this.kind = kind;
    }

    public PostEntity(int id, int userID, String content, PostKind kind, int originalPostID) {
        this.id = id;
        this.userID = userID;
        this.content = content;
        this.kind = kind;
        this.originalPostID = originalPostID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }
    public void setDate(String datum) {
        this.date = datum;
    }

    @Column(name = "postKind", nullable = false)
    public PostKind getKind() {
        return kind;
    }
    public void setKind(PostKind kind) {
        this.kind = kind;
    }

    @Column(name = "OP_id", nullable = false)
    public int getOriginalPostID() {
        return originalPostID;
    }
    public void setOriginalPostID(int originalPostID) {
        this.originalPostID = originalPostID;
    }
}
