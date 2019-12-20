package com.bigidea.twitter.classes.Chat.interfaces;

import com.bigidea.twitter.classes.Chat.interfaces.IChat;
import com.bigidea.twitter.classes.User.User;

public interface IChatManager {
    void sendMessage(User user,String content);
    void closeChat();
    IChat getChat();
}
