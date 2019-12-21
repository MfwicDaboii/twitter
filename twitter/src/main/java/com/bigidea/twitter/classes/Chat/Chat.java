package com.bigidea.twitter.classes.Chat;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.Chat.interfaces.IChat;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Chat implements IChat {
    private int id;
    private ArrayList<Message> messages;
    private ArrayList<User> users;

    public Chat(){}

    public Chat(ArrayList<User> users) {
        this.users = users;
        messages = new ArrayList<>();
        SecureRandom rnd = new SecureRandom();
        id = rnd.nextInt(9999);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getId(){
        return this.id;
    }

}
