package com.bigidea.twitter.websockets.DTOs;

public class LoginDTO {
    private String username;
    private String password;
    private boolean state;

    public  LoginDTO(){}

    public  LoginDTO(boolean state){
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
}
