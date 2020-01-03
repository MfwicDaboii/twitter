package com.bigidea.twitter.rest.Entities;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class LikeEntity {
    private int id;
    private int userID;
    private int postID;

    public LikeEntity(){}
    public LikeEntity(int id, int userID, int postID) {
        this.id = id;
        this.userID = userID;
        this.postID = postID;
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

    @Column(name = "post_id", nullable = false)
    public int getPostID() {
        return postID;
    }
    public void setPostID(int postID) {
        this.postID = postID;
    }
}
