package com.bigidea.twitter.rest.Entities;

import com.bigidea.twitter.enumerations.Topic;

import javax.persistence.*;

@Entity
@Table(name = "hashtags")
public class HashTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "topic", nullable = false)
    private Topic topic;

    public HashTagEntity(){}
    public HashTagEntity(String name, Topic topic) {
        this.name = name;
        this.topic = topic;
    }
    public HashTagEntity(int id, String name, Topic topio){
        this.id = id;
        this.name = name;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
