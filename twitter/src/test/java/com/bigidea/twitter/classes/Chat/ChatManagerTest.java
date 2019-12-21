package com.bigidea.twitter.classes.Chat;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Chat.interfaces.IChatManager;
import com.bigidea.twitter.websockets.DTOs.ChatDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChatManagerTest {
    private IChatManager manager;
    private ArrayList<User> users;
    private String content;

    @BeforeEach
    void setUp(){
        users = new ArrayList<>();
        users.add(new User("Main","user",20, Gender.OTHER,"OnLyme"));
        users.add(new User("Other","user",20, Gender.OTHER,"OffLyme"));
        content = "Some content";
        manager = new ChatManager();
    }

    @AfterEach
    void tearDown(){
        users = null;
        content = null;
        manager = null;
    }

    @Test
    void sendMessage() {
        //Arrange
        User main = users.get(0);
        ChatDTO dto;

        //Act
        dto = manager.create(users);
        manager.sendMessage(dto.getChatID(),main,content);
        Message msg = manager.getChatById(dto.getChatID()).getMessages().get(0);

        //Assert
        assertEquals(content, msg.getContent());
        assertEquals(main,msg.getUser());
    }

    @Test
    void closeChat() {
        //Assert
        ChatDTO dto;

        //Act
        dto = manager.create(users);
        manager.closeChat(dto.getChatID());

        //Assert
        assertNull(manager.getChatById(dto.getChatID()));
    }
}