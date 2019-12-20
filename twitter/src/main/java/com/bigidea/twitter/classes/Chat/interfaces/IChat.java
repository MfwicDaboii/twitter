package com.bigidea.twitter.classes.Chat.interfaces;

import com.bigidea.twitter.classes.Chat.Message;
import com.bigidea.twitter.classes.User.User;

import java.util.ArrayList;

public interface IChat {
    ArrayList<Message> getMessages();
    ArrayList<User> getUsers();
}
