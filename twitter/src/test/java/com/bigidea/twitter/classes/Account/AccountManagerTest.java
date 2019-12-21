package com.bigidea.twitter.classes.Account;

import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Account.interfaces.IAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {
    private AccountManager accountManager = new AccountManager();
    private String username;
    private String password;

    @BeforeEach
    void setUp(){
        username = "Mfwic";
        password = "daboi";
    }
    @AfterEach
    void tearDown(){
        Account.setInstance(null);
        accountManager = null;
    }
    @Test
    void login() {
        //Arrange
        boolean result = false;

        //Act
        accountManager.register(username,"Password123!");
        result = accountManager.login(username,"Password123!");

        //Assert
        assertEquals(true, result);
    }
    @Test
    void loginWrongCredentials() {
        //Arrange
        password = "WRONG!";
        boolean result = false;

        //Act
        result = accountManager.login(username,password);

        //Assert
        assertEquals(false, result);
    }
    @Test
    void loginCredentialsEmpty() {
        //Arrange
        password = "";
        username = "";
        boolean result = false;

        //Act
        result = accountManager.login(username,password);

        //Assert
        assertEquals(false, result);
    }
    @Test
    void loginCredentialsNull() {
        //Arrange
        boolean result = false;

        //Act
        result = accountManager.login(null,null);

        //Assert
        assertEquals(false, result);
    }
    @Test
    void register() {
        //Arrange
        String name = "Mr. Game & Watch";
        String secret = "Password123!";

        //Act
        int id =  accountManager.register(name,secret);

        //Assert
       assertNotNull(id);
    }
    @Test
    void registerWrongPassword() {
        //Arrange
        String name = "Mr. Game & Watch";
        String secret = "wrongKindOfPassword";

        //Act
        int id =  accountManager.register(name,secret);

        //Assert
        assertEquals(-1, id);
    }
    @Test
    void registerCredentialsEmpty() {
        //Arrange
        String name = "";
        String secret = "";

        //Act
        int id =  accountManager.register(name,secret);

        //Assert
        assertEquals(-1, id);
    }
    @Test
    void registerCredentialsNull() {
        //Arrange

        //Act
        int id =  accountManager.register(null,null);

        //Assert
        assertEquals(-1, id);
    }
    @Test
    void changeCredentials(){
        //Arrange
        String username = "MyNewName";
        String password = "Secret123!";
        boolean result = false;

        //Act
        int id = accountManager.register("test","Password123!");
        result =accountManager.changeCredentials(id,username, password);

        //Assert
        assertEquals(true, result);
    }

    @Test
    void changeCredentialsEmpty(){
        //Arrange
        boolean result = false;

        //Act
        accountManager.register("test","Password123!");
        result =accountManager.changeCredentials(10,null, null);

        //Assert
        assertEquals(false, result);
    }
    @Test
    void addUser(){
        //Arrange
        int id = accountManager.register("test","Password123!");

        //Act
        Account a = accountManager.getAccountById(id);
        accountManager.addUser(id,"firstName", "lastName", 1, Gender.OTHER, "text.exe");

        //Assert
        assertEquals("firstName", a.getUser().getFirstName());
        assertEquals("lastName", a.getUser().getLastName());
        assertEquals(1, a.getUser().getAge());
        assertEquals(Gender.OTHER, a.getUser().getGender());
        assertEquals("text.exe", a.getUser().getBiography());
    }
}