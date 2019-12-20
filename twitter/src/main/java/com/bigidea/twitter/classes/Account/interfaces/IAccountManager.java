package com.bigidea.twitter.classes.Account.interfaces;

public interface IAccountManager {
    boolean login(String username, String password);
    IAccount register(String username, String password);
    boolean checkPassword(String password);
    boolean changeCredentials(String username, String password);
}
