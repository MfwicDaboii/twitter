package com.bigidea.twitter.classes.Account;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Account.interfaces.IAccountManager;

import java.util.ArrayList;
import java.util.List;

public class AccountManager implements IAccountManager {
    static List<Account> accounts = new ArrayList<>();

    public AccountManager(){}

    public AccountManager(List<Account> all) {
       accounts = all;
    }

    @Override
    public boolean login(String username, String password) {
        try{
            for (Account a: accounts){
                if(a.getUsername().equals(username) && a.getPassword().equals(password)){
                    return true;
                }
            }
        }catch(NullPointerException exception){
            return false;
        }
        return false;
    }

    @Override
    public int register(String username, String password) {
        Account account;
        if(checkPassword(password)){
            account = new Account(username, password);
            accounts.add(account);
        }else {
            return -1;
        }
       return account.getId();
    }

    @Override
    public boolean checkPassword(String password) {
        try{
            PasswordValidator validator = new PasswordValidator();
            return validator.validate(password);
        }catch(NullPointerException exception){
            return false;
        }
    }

    @Override
    public boolean changeCredentials(int id, String username, String password) {
        Account account = getAccountById(id);
        if(checkPassword(password)){
            account.updateCredentials(username,password);
           return true;
        }
        return false;
    }

    public void addUser(int id,String firstName, String lastName, int age, Gender gender, String biography){
        Account account = getAccountById(id);
        account.setUser(new User(firstName,lastName,age,gender,biography));
    }

    public Account getAccountById(int id){
        for(Account a: accounts){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }

}
