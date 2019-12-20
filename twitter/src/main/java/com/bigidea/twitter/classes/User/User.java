package com.bigidea.twitter.classes.User;

import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.User.interfaces.IUser;

import java.security.SecureRandom;
import java.util.ArrayList;

public class User implements IUser {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String biography;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<Post> timeline;

    public User(String firstName, String lastName, int age, Gender gender, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.timeline = new ArrayList<>();
        this.gender = gender;
        this.biography = biography;

        SecureRandom rnd = new SecureRandom();
        id = rnd.nextInt(9999);
    }

    public User(int id, String firstName, String lastName, int age, Gender gender, String biography, ArrayList<User> followers, ArrayList<User> following, ArrayList<Post> timeline) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.biography = biography;
        this.followers = followers;
        this.following = following;
        this.timeline = timeline;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    public int getId() {
        return id;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public ArrayList<Post> getTimeline() {
        return timeline;
    }

    @Override
    public void updateTimeline(Post post) {
        timeline.add(post);
    }

    @Override
    public void updateFollowers(User user, boolean follow) {
        if(follow){
            followers.add(user);
        }else{
            followers.remove(user);
        }
    }

    @Override
    public void updateFollowing(User user, boolean follow) {
        if(follow){
            following.add(user);
        }else{
            following.remove(user);
        }
    }


}
