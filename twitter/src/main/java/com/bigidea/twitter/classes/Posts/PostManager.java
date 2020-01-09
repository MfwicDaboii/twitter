package com.bigidea.twitter.classes.Posts;

import com.bigidea.twitter.classes.Hashtag.HashTag;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.classes.Posts.interfaces.IPostManager;

import java.util.ArrayList;

public class PostManager implements IPostManager {

    public PostManager(){}

    @Override
    public Post postTweet(User user,String content) {
        ArrayList<HashTag> hashTags = createTags(splitContent(content));
        Tweet tweet = new Tweet(content, hashTags);
        user.updateTimeline(tweet);
        return tweet;
    }

    @Override
    public Post postReTweet(User user,String content, Tweet originalTweet) {
        ArrayList<HashTag> hashTags = createTags(splitContent(content));
        ReTweet reTweet = new ReTweet(content,originalTweet,hashTags);
        user.updateTimeline(reTweet);
        return reTweet;
    }

    @Override
    public Post postComment(User user,String content,Post post) {
        ArrayList<HashTag> hashTags = createTags(splitContent(content));
        Comment comment = new Comment(content,hashTags);
        user.updateTimeline(comment);
        return comment;
    }

    @Override
    public ArrayList<String> splitContent(String content) {
        int x = content.indexOf('#',0);
        int y = 0;
        String topic;
        ArrayList<String> topics = new ArrayList<>();

        while(x != -1){
            //Find the hashTag
            x = content.indexOf('#',y);
            if(y >= 0 && x != -1){
                //Get the position where the tag ends
                y = content.indexOf(' ',x);
                //Get the tag
                if(y == -1){
                    topic = content.substring(x);
                }else{
                    topic = content.substring(x,y);
                }
                //Add to the list
                topics.add(topic);
            }else{
                x = -1;
            }
        }
        return topics;
    }

    @Override
    public ArrayList<HashTag> createTags(ArrayList<String> topics) {
        ArrayList<HashTag> hashTags = new ArrayList<>();
        for (String s: topics) {
            HashTag tag = new HashTag(s);
            hashTags.add(tag);
        }
        return  hashTags;
    }

    @Override
    public void like(int userId,Post post ,boolean isLike) {
        if(isLike){
            post.getLikes().add(userId);
        }else{
            for(int i = 0; i< post.getLikes().size(); i++){
                if(post.getLikes().get(i) == userId){
                    post.getLikes().remove(i);
                }
            }
        }
    }

    public Tweet getPostById(User user, int id){
        for (Post p: user.getTimeline()) {
            if(p.getId()== id){
              return new Tweet(id,p.getContent(),p.getHashTags());
            }
        }
        return null;
    }
}
