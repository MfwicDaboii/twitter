package com.bigidea.twitter.websockets.DTOs;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.enumerations.Gender;

import java.util.ArrayList;

public class UserDTO {
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;
    private String biography;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private boolean isFollwing;

    public UserDTO(){}

    public UserDTO(String firstName, String lastName, int age, Gender gender, String biography, ArrayList<User> followers, ArrayList<User> following, boolean IsFollowing) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.biography = biography;
        this.followers = followers;
        this.following = following;
        this.isFollwing = IsFollowing;
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

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

    public boolean isFollwing() {
        return isFollwing;
    }

    public void setFollwing(boolean follwing) {
        isFollwing = follwing;
    }
}
