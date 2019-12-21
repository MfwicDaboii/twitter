package com.bigidea.twitter.classes.Chat.interfaces;

import com.bigidea.twitter.classes.Chat.Chat;
import com.bigidea.twitter.classes.Chat.Message;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.websockets.DTOs.ChatDTO;

import java.util.ArrayList;

public interface IChatManager {
    ChatDTO create(ArrayList<User> users);
    ArrayList<Message> sendMessage(int id, User user, String content);
    void closeChat(int id);
    Chat getChatById(int id);
}
