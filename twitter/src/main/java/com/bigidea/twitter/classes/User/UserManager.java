package com.bigidea.twitter.classes.User;

import com.bigidea.twitter.classes.User.interfaces.IUserManager;

import java.util.ArrayList;
import java.util.List;

public class UserManager implements IUserManager {
    static List<List<User>> users = new ArrayList<>();

    public UserManager(){
        for(int i = 0; i < 26; i++){
            users.add(new ArrayList<>());
        }
    }

    @Override
    public void follow(User user, User user2) {
        user.updateFollowing(user2, true);
    }

    @Override
    public void unFollow(User user, User user2) {
        user.updateFollowing(user2, false);
    }

    @Override
    public void addFollower(User user, User user2) {
       user.updateFollowers(user2,true);
    }

    @Override
    public void removeFollower(User user, User user2) {
        user.updateFollowers(user2,false);
    }

    @Override
    public User getUserById(int id){
        for (List<User> list: users) {
            for(User u: list){
                if(u.getId() == id){
                    return u;
                }
            }
        }
        return null;
    }

    public void addUserToList(int position, User user){
        users.get(position).add(user);
    }

    public List<User> getUserByName(int position, String element){
        List<User> result = new ArrayList<>();
        for(User u: users.get(position)){
            if(element.matches(u.getFirstName())){
                result.add(u);
            }
       }
        return result;
    }

    public int getPosition(String character){
       switch(character.charAt(0)){
           case 'a':
           case 'A':
               return 0;
           case 'b':
           case 'B':
               return 1;
           case 'c':
           case 'C':
               return 2;
           case 'd':
           case 'D':
               return 3;
           case 'e':
           case 'E':
               return 4;
           case 'f':
           case 'F':
               return 5;
           case 'g':
           case 'G':
               return 6;
           case 'h':
           case 'H':
               return 7;
           case 'i':
           case 'I':
               return 8;
           case 'j':
           case 'J':
               return 9;
           case 'k':
           case 'K':
               return 10;
           case 'l':
           case 'L':
               return 11;
           case 'm':
           case 'M':
               return 12;
           case 'n':
           case 'N':
               return 13;
           case 'o':
           case 'O':
               return 14;
           case 'p':
           case 'P':
               return 15;
           case 'q':
           case 'Q':
               return 16;
           case 'r':
           case 'R':
               return 17;
           case 's':
           case 'S':
               return 18;
           case 't':
           case 'T':
               return 6;
           case 'u':
           case 'U':
               return 19;
           case 'v':
           case 'V':
               return 20;
           case 'w':
           case 'W':
               return 21;
           case 'x':
           case 'X':
               return 22;
           case 'y':
           case 'Y':
               return 23;
           case 'z':
           case 'Z':
               return 24;
           default:
               return 25;
       }
    }
}
