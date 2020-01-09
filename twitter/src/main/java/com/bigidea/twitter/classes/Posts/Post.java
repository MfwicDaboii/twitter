package com.bigidea.twitter.classes.Posts;

import com.bigidea.twitter.classes.Hashtag.HashTag;
import com.bigidea.twitter.enumerations.PostKind;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Post{
    private int id;
    private String content;
    private PostKind postKind;
    private LocalDateTime date;
    private ArrayList<Integer> likes;
    private ArrayList<HashTag> hashTags;

    public Post(){}

    public Post(String content, PostKind postKind, ArrayList<HashTag> HashTags) {
        this.content = content;
        this.postKind = postKind;
        this.hashTags = HashTags;
        likes = new ArrayList<>();
        date = LocalDateTime.now();
        SecureRandom rnd = new SecureRandom();
        id = rnd.nextInt(9999);

    }

    public Post(int Id, String content, PostKind postKind,ArrayList<HashTag> HashTags) {
        this.id = Id;
        this.content = content;
        this.postKind = postKind;
        this.hashTags = HashTags;
        likes = new ArrayList<>();
        date = LocalDateTime.now();
    }

    public int getId(){
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Integer> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Integer> likes) {
        this.likes = likes;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(ArrayList<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public PostKind getPostKind() {
        return postKind;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", date=" + date +
                ", hashTags=" + hashTags +
                ", postKind=" + postKind +
                '}';
    }
}
