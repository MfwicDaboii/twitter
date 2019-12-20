package com.bigidea.twitter.classes.Account;

import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Account.interfaces.IAccount;
import com.bigidea.twitter.classes.Account.interfaces.IAccountManager;

public class AccountManager implements IAccountManager {
    private IAccount account;

    public AccountManager(){}

    public AccountManager(String username, String password) {
        this.account = getAccount(username,password);
    }

    public IAccount getAccount(String username, String password) {
        return new Account(username, password);
    }

    @Override
    public boolean login(String username, String password) {
        boolean state = false;
        try{
            if(username.equals(account.getUsername()) && password.equals(account.getPassword())){
                state = true;
            }
        }catch(NullPointerException exception){
            return false;
        }
        return state;
    }

    @Override
    public IAccount register(String username, String password) {
        if(checkPassword(password)){
            account = new Account(username, password);
        }else {
            return null;
        }
       return account;
    }

    @Override
    public boolean checkPassword(String password) {
        boolean result = false;
        try{
            PasswordValidator validator = new PasswordValidator();
            result = validator.validate(password);
        }catch(NullPointerException exception){
            return false;
        }
        return result;
    }

    @Override
    public boolean changeCredentials(String username, String password) {
        boolean state = false;
        if(checkPassword(password)){
            this.account.updateCredentials(username,password);
            state = true;
        }
        return state;
    }

    public void addUser(String firstName, String lastName, int age, Gender gender, String biography){
       account.setUser(new User(firstName,lastName,age,gender,biography));
    }
}
