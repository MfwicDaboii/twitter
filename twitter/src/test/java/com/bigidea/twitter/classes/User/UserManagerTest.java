package com.bigidea.twitter.classes.User;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Account.interfaces.IAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    private UserManager manager;
    private User mainUser;
    private User otherUser;
    private IAccount account;

    @BeforeEach
    void setup(){
        account = new Account("amdin","password");
        mainUser = new User("Joshua", "Keulers", 21, Gender.MALE,"bla bla....");
        otherUser = new User("Bot","Bot",69,Gender.OTHER,"Biep Boep!");
        manager = new UserManager();
    }

    @AfterEach
    void tearDown(){
        account = null;
        mainUser = null;
        otherUser = null;
        manager = null;
    }

    @Test
    void follow() {
        //Act
        manager.follow(mainUser, otherUser);
        manager.addFollower(otherUser, mainUser);

        //Assert
        assertEquals(1, mainUser.getFollowing().size());
        assertEquals(1, otherUser.getFollowers().size());
    }

    @Test
    void unFollow() {
        //Act
        manager.follow(mainUser, otherUser);
        manager.addFollower(otherUser, mainUser);

        manager.unFollow(mainUser, otherUser);
        manager.removeFollower(otherUser, mainUser);


        //Assert
        assertEquals(0,mainUser.getFollowing().size() );
        assertEquals(0, otherUser.getFollowers().size());
    }


}