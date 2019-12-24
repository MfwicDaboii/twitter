package com.bigidea.twitter.websockets.DTOs;

public class LoginDTO {
    private int key;
    private String username;
    private String password;
    private boolean state;

    public  LoginDTO(){}

    public  LoginDTO(boolean state){
        this.state = state;
    }

    public  LoginDTO(boolean state, int key){
        this.key = key;
        this.state = state;
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
