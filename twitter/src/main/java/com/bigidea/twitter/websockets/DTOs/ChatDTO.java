package com.bigidea.twitter.websockets.DTOs;

import com.bigidea.twitter.classes.Chat.Message;
import com.bigidea.twitter.classes.User.User;

import java.util.ArrayList;

public class ChatDTO {
    private int chatID;
    private User user;
    private ArrayList<Message> messages;

    public ChatDTO(){}

    public ChatDTO(ArrayList<Message> messages){this.messages = messages;}

    public ChatDTO(int chatID, User user) {
        this.chatID = chatID;
        this.user = user;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
