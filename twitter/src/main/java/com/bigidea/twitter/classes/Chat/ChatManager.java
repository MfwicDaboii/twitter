package com.bigidea.twitter.classes.Chat;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.Chat.interfaces.IChat;
import com.bigidea.twitter.classes.Chat.interfaces.IChatManager;
import com.bigidea.twitter.websockets.DTOs.ChatDTO;

import java.util.ArrayList;
import java.util.List;

public class ChatManager implements IChatManager {
    static List<Chat> chats=  new ArrayList<>();

    public ChatManager(){}

    @Override
    public ChatDTO create(ArrayList<User> users){
        Chat c = new Chat(users);
        chats.add(c);
        return new ChatDTO(c.getId(), users.get(0));
    }

    @Override
    public ArrayList<Message> sendMessage(int id, User user,String content) {
        System.out.println("[Chat - Send message] [User " + user.getFirstName() + "]");
        Chat chat = getChatById(id);
        Message msg = new Message(user,content);
        chat.getMessages().add(msg);
        return chat.getMessages();
    }

    @Override
    public void closeChat(int id) {
       Chat chat = getChatById(id);
       chats.remove(chat);
    }

    @Override
    public Chat getChatById(int id){
        for(Chat c: chats){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
}
