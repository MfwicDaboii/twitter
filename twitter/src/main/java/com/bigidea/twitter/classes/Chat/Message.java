package com.bigidea.twitter.classes.Chat;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.User.interfaces.IUser;

import java.time.LocalDateTime;

public class Message {
    private IUser user;
    private String content;
    private LocalDateTime date;
    private boolean isRead;

    public Message(){}

    public Message(User User, String Content){
        user = User;
        content = Content;
        date = LocalDateTime.now();
        isRead = false;
    }

    public IUser getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", isRead=" + isRead +
                '}';
    }
}
