package com.bigidea.twitter.rest.Entities;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    private int id;
    private String username;
    private String password;
    private int user_id;

    public AccountEntity(){}

    public AccountEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountEntity(String username, String password, int user_id) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return  id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    @Column(name = "user_id", nullable = false)
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_id='" + user_id +'\'' +
                '}';
    }
}
