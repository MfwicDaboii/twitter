package com.bigidea.twitter.classes;

import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.User.User;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    public List<Post> sortPostsByDate(List<Post> sorted, List<Post> unsorted){
        if(sorted.isEmpty()){
            return unsorted;
        }
        int length  =  sorted.size() + unsorted.size();
        int uPos = 0;
        int sPos = 0;
        List<Post> newList = new ArrayList<>();

        for(int i = 0; i < length; i++){
            int result = sorted.get(sPos).getDate().compareTo(unsorted.get(uPos).getDate()); //compareTo() returns -1,0,1

            switch (result){
                case -1: //Als de post van de sorted list eerder is
                    newList.add(sorted.get(sPos));
                    sPos++;
                    break;
                case 0: //Als de post van de sorted list en unsorted list gelijk zijn
                    newList.add(sorted.get(sPos));
                    sPos++;
                    newList.add(sorted.get(uPos));
                    uPos++;
                    break;
                case 1: //Als de post van de unsorted list eerder is
                    newList.add(sorted.get(uPos));
                    uPos++;
                    break;
            }
        }


        return newList;
    }
}
