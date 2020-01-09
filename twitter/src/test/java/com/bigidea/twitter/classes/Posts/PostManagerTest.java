package com.bigidea.twitter.classes.Posts;

import com.bigidea.twitter.classes.Hashtag.HashTag;
import com.bigidea.twitter.classes.User.User;
import com.bigidea.twitter.enumerations.Gender;
import com.bigidea.twitter.classes.Posts.interfaces.IPostManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PostManagerTest {
    private Tweet tweet;
    private IPostManager manager;
    private User user;

    @BeforeEach
    void setUp(){
        user = new User(1,"Joshua", "Keulers", 21, Gender.MALE,"bla bla....",  new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
        tweet = new Tweet(1,"Gimma ma goddamn", new ArrayList<>(),new ArrayList<>(), new ArrayList<>());
        manager = new PostManager();
    }

    @AfterEach
    void tearDown(){
        tweet = null;
        user = null;
        manager = null;
    }

    @Test
    void postTweet() {
        //Arrange
        String content = "Hello world!";

        //Act
        manager.postTweet(user,content);
        Post post = user.getTimeline().get(0);

        //Assert
        assertEquals(content, post.getContent());
    }

    @Test
    void postReTweet() {
        //Arrange
        String content = "JUST BE PATIENT!";

        //Act
        manager.postReTweet(user,content,tweet);
        Post post = user.getTimeline().get(0);

        //Assert
        assertEquals("JUST BE PATIENT!", post.getContent());
    }

    @Test
    void postComment() {
        //Arrange
        String content = "JUST BE PATIENT!";

        //Act
        manager.postComment(user,content,tweet);
        Post post = user.getTimeline().get(0);

        //Assert
        assertEquals("JUST BE PATIENT!", post.getContent());
    }

    @Test
    void splitContent(){
        //Arrange
        String testContent = "i love dogs #Dog #Tests ";
        ArrayList<String> result = new ArrayList<>();

        //Act
        result = manager.splitContent(testContent);

        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void splitContentNoHashTags(){
        //Arrange
        String testContent = "i love dogs";
        ArrayList<String> result = new ArrayList<>();

        //Act
        result = manager.splitContent(testContent);

        //Assert
        assertEquals(0, result.size());
    }

    @Test
    void createTags(){
        //Arrange
        String testContent = "i love dogs #Doggo";
        ArrayList<String> topics;
        ArrayList<HashTag> tags;

        //Act
        topics = manager.splitContent(testContent);
        tags = manager.createTags(topics);

        //Assert
        assertEquals(1, tags.size());
    }

    @Test
    void like(){
        //Arrange
        manager.like(user.getId(),tweet,true);

        //Assert
        assertEquals(1, tweet.getLikes().size());
    }

    @Test
    void unLike(){
        //Arrange
        manager.like(user.getId(),tweet,true);
        manager.like(user.getId(),tweet,false);

        //Assert
        assertEquals(0, tweet.getLikes().size());
    }
}