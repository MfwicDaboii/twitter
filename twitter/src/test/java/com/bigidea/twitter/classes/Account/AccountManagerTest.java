package com.bigidea.twitter.classes.Account;

import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Account.interfaces.IAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountManagerTest {
    private IAccount account;
    private AccountManager accountManager;
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
        account = new Account(username,password);
        accountManager = new AccountManager(username, password);
        boolean result = false;

        //Act
        result = accountManager.login(username,password);

        //Assert
        assertEquals(true, result);
    }
    @Test
    void loginWrongCredentials() {
        //Arrange
        accountManager = new AccountManager(username, password);
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
        accountManager = new AccountManager(username, password);
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
        accountManager = new AccountManager(username, password);
        boolean result = false;

        //Act
        result = accountManager.login(null,null);

        //Assert
        assertEquals(false, result);
    }
    @Test
    void register() {
        //Arrange
        AccountManager um = new AccountManager();
        String name = "Mr. Game & Watch";
        String secret = "Password123!";

        //Act
        IAccount acc =  um.register(name,secret);

        //Assert
        assertEquals(name, acc.getUsername());
    }
    @Test
    void registerWrongPassword() {
        //Arrange
        AccountManager um = new AccountManager();
        String name = "Mr. Game & Watch";
        String secret = "wrongKindOfPassword";

        //Act
        IAccount acc =  um.register(name,secret);

        //Assert
        assertNull(acc);
    }
    @Test
    void registerCredentialsEmpty() {
        //Arrange
        AccountManager um = new AccountManager();
        String name = "";
        String secret = "";

        //Act
        IAccount acc =  um.register(name,secret);

        //Assert
        assertNull(acc);
    }
    @Test
    void registerCredentialsNull() {
        //Arrange
        AccountManager um = new AccountManager();

        //Act
        IAccount acc =  um.register(null,null);

        //Assert
        assertNull(acc);
    }
    @Test
    void changeCredentials(){
        //Arrange
        String username = "MyNewName";
        String password = "Secret123!";
        boolean result = false;

        //Act
        accountManager = new AccountManager(this.username, this.password);
        accountManager.login(this.username, this.password);
        result =accountManager.changeCredentials(username, password);

        //Assert
        assertEquals(true, result);
    }
    @Test
    void changeWrongCredentials(){
        //Arrange
        String username = "MyNewName";
        String password = "Secret";
        boolean result = false;

        //Act
        accountManager = new AccountManager(this.username, this.password);
        accountManager.login(this.username, this.password);
        result =accountManager.changeCredentials(username, password);

        //Assert
        assertEquals(false, result);
    }
    @Test
    void changeCredentialsEmpty(){
        //Arrange
        boolean result = false;

        //Act
        accountManager = new AccountManager(this.username, this.password);
        accountManager.login(this.username, this.password);
        result =accountManager.changeCredentials(null, null);

        //Assert
        assertEquals(false, result);
    }
    @Test
    void addUser(){
        //Arrange
        account = new Account(username,password);
        accountManager = new AccountManager(username, password);

        //Act
        accountManager.addUser("firstName", "lastName", 1, Gender.OTHER, "text.exe");

        //Assert
        assertEquals("firstName", account.getUser().getFirstName());
        assertEquals("lastName", account.getUser().getLastName());
        assertEquals(1, account.getUser().getAge());
        assertEquals(Gender.OTHER, account.getUser().getGender());
        assertEquals("text.exe", account.getUser().getBiography());
    }
}