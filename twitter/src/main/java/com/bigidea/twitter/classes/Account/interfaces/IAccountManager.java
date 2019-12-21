package com.bigidea.twitter.classes.Account.interfaces;

public interface IAccountManager {
    int login(String username, String password);
    int register(String username, String password);
    boolean checkPassword(String password);
    boolean changeCredentials(int id,String username, String password);
}
