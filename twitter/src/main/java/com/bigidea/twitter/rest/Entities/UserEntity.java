package com.bigidea.twitter.rest.Entities;

import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.enumerations.PostKind;

import javax.persistence.*;

@Entity
@Table(name = "follow")
public class UserEntity {
    private int id;
    private String firstname;
    private String lastname;
    private int age;
    private Gender gender;
    private String biography;

    public UserEntity(){}

    public UserEntity(String firstname, String lastname, int age, Gender gender, String biography) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.biography = biography;
    }

    public UserEntity(int id, String firstname, String lastname, int age, Gender gender, String biography) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.biography = biography;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "firstname", nullable = false)
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname", nullable = false)
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "age", nullable = false)
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "gender", nullable = false)
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = "biography", nullable = false)
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
}
