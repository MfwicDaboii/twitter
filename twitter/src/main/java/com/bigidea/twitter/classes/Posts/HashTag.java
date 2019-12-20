package com.bigidea.twitter.classes.Posts;

public class HashTag {
    private int id;
    private String topic;

    public HashTag(String Topic){
        topic = Topic;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
