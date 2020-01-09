package com.bigidea.twitter.classes;

import com.bigidea.twitter.classes.Posts.Post;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.rest.Entities.PostEntity;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {
    private int counter = 0;
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

    public List<PostEntity> BinarySearch(List<PostEntity> array, String date){
        List<Integer> searchElements = this.splitDate(date);
        List<PostEntity> allPossiblePosts = new ArrayList<>();

        allPossiblePosts = array;
        while(counter != 3){
            allPossiblePosts = getPossiblePosts(allPossiblePosts,searchElements.get(counter));
           counter++;
        }
        return allPossiblePosts;
    }

    public List<Integer> splitDate(String date){
        String[] strings = date.split("-");
        List<Integer> dates = new ArrayList<>();
        for(String s:strings){
            dates.add(Integer.parseInt(s));
        }
        return dates;
    }

    public PostEntity getMiddleListItem(List<PostEntity> list){
        return list.get(list.size()/2);
    }

    public int compare(int searchItem, int listItem){
        if(searchItem == listItem){
            return 0;
        }else if (searchItem < listItem){
            return -1;
        }else{
            return 1;
        }
    }

    public List<PostEntity> splitList(List<PostEntity> arr, int position ,int result){
        if(result==1){
            return arr.subList(position, arr.size());
        }else if(result == -1){
            return arr.subList(0, position);
        }else{
           List<PostEntity> posts = new ArrayList<>();
           posts.add(arr.get(position));
           return posts;
        }
    }

    public List<PostEntity> checkLeft(int startPosition , List<PostEntity> originalList ,List<PostEntity> newList, int searchElement){
        int checkPosition = startPosition - 1;
        try{
            PostEntity entity = originalList.get(checkPosition);
            if(compare(searchElement, splitDate(entity.getDate()).get(counter)) == 0){
                newList.add(entity);
                checkLeft(checkPosition,originalList,newList, searchElement);
            }
            return newList;
        }catch(IndexOutOfBoundsException ex){
            return newList;
        }
    }

    public List<PostEntity> checkRight(int startPosition , List<PostEntity> originalList ,List<PostEntity> newList, int searchElement){
        int checkPosition = startPosition + 1;
        try{
            PostEntity entity = originalList.get(checkPosition);
            if(compare(searchElement, splitDate(entity.getDate()).get(counter)) == 0){
                newList.add(entity);
                checkRight(checkPosition,originalList,newList, searchElement);
            }
            return newList;
        }catch(IndexOutOfBoundsException ex){
            return newList;
        }
    }

    public List<PostEntity> getPossiblePosts(List<PostEntity> list, int searchElement ){
          List<PostEntity> possiblePosts = new ArrayList<>();
          //Get the item at the middle of the list
          PostEntity entity = getMiddleListItem(list);
          //Get the element to compare to
          int compareElement = this.splitDate(entity.getDate()).get(counter);
          //Compare the date with the other element;
          int result = compare(searchElement,compareElement);
          //Get the middle position of the list
          int startPosition = list.size()/2 ;
          if(result == 0){
              possiblePosts.add(entity);
          }
          while(result != 0){
              //Split the list and do it again based on the results
              List<PostEntity> posts = splitList(list,startPosition,result);
              list = posts;
              compareElement = this.splitDate(this.getMiddleListItem(list).getDate()).get(0);
              startPosition = list.size()/2 ;
              result = compare(searchElement,compareElement);
              if(list.size() == 1 && result!=0){
                  return new ArrayList<>();
              }else if(list.size() > 1 && result==0){
                  possiblePosts.add(this.getMiddleListItem(list));
              }
          }

          possiblePosts = checkLeft(startPosition, list,possiblePosts, searchElement);

          possiblePosts = checkRight(startPosition, list,possiblePosts, searchElement);

          return possiblePosts;
    }
}
