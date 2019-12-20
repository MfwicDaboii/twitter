package com.bigidea.twitter.classes.Chat;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.Chat.interfaces.IChat;
import com.bigidea.twitter.classes.Chat.interfaces.IChatManager;

import java.util.ArrayList;

public class ChatManager implements IChatManager {
    private IChat chat;

    public ChatManager(ArrayList<User> users){
        this.chat = new Chat(users);
    }

    public IChat getChat() {
        return chat;
    }

    @Override
    public void sendMessage(User user,String content) {
        System.out.println("[Chat - Send message] [User " + user.getFirstName() + "]");
        Message msg = new Message(user,content);
        chat.getMessages().add(msg);
    }

    @Override
    public void closeChat() {
        System.out.println("[Chat - closing chat]");
        chat.getMessages().clear();
        chat.getUsers().clear();
        chat = null;
    }
}
