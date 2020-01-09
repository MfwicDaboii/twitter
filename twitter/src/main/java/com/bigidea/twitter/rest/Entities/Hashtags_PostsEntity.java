package com.bigidea.twitter.rest.Entities;

import javax.persistence.*;

@Entity
@Table(name = "hashtags_post")
public class Hashtags_PostsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "tag_id", nullable = false)
    private int tag_id;
    @Column(name = "post_id", nullable = false)
    private int post_id;

    public Hashtags_PostsEntity(){}
    public Hashtags_PostsEntity(int tag_id, int post_id) {
        this.tag_id = tag_id;
        this.post_id = post_id;
    }
    public Hashtags_PostsEntity(int id, int tag_id, int post_id) {
        this.id = id;
        this.tag_id = tag_id;
        this.post_id = post_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
}
