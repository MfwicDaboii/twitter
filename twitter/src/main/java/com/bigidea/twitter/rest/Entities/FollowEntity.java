package com.bigidea.twitter.rest.Entities;

import javax.persistence.*;

@Entity
@Table(name = "follow")
public class FollowEntity {
    private int id;
    private int userID;
    private int followerID;

    public FollowEntity(){}
    public FollowEntity(int id, int userID, int followerID) {
        this.id = id;
        this.userID = userID;
        this.followerID = followerID;
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

    @Column(name = "follower_id", nullable = false)
    public int getFollowerID() {
        return followerID;
    }
    public void setFollowerID(int followerID) {
        this.followerID = followerID;
    }
}
