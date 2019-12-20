package com.bigidea.twitter.classes.User;

import com.bigidea.twitter.classes.Account.Account;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Account.interfaces.IAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    private UserManager mainUserManager;
    private UserManager otherUserManager;
    private User mainUser;
    private User otherUser;
    private IAccount account;

    @BeforeEach
    void setup(){
        account = new Account("amdin","password");
        mainUser = new User("Joshua", "Keulers", 21, Gender.MALE,"bla bla....");
        otherUser = new User("Bot","Bot",69,Gender.OTHER,"Biep Boep!");
        mainUserManager = new UserManager(mainUser);
        otherUserManager = new UserManager(otherUser);
    }

    @AfterEach
    void tearDown(){
        account = null;
        mainUser = null;
        otherUser = null;
        mainUserManager = null;
        otherUserManager = null;
    }

    @Test
    void follow() {
        //Act
        otherUserManager.follow(mainUser);
        mainUserManager.follow(otherUser);

        otherUserManager.addFollower(mainUser);
        mainUserManager.addFollower(otherUser);

        //Assert
        assertEquals(1, mainUser.getFollowing().size());
        assertEquals(1, mainUser.getFollowers().size());
    }

    @Test
    void unFollow() {
        //Act
        mainUserManager.follow(otherUser);
        otherUserManager.addFollower(mainUser);

        mainUserManager.unFollow(otherUser);
        otherUserManager.removeFollower(mainUser);


        //Assert
        assertEquals(0,mainUser.getFollowing().size() );
        assertEquals(0, mainUser.getFollowers().size());
    }


}