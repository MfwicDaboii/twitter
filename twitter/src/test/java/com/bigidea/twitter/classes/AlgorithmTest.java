package com.bigidea.twitter.classes;

import com.bigidea.twitter.enumerations.PostKind;
import com.bigidea.twitter.rest.Entities.PostEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {
    private List<PostEntity> allPosts;
    private String searchDate;
    private Algorithm algorithm;

    @BeforeEach
    void setUp() {
        this.allPosts = new ArrayList<>();
        this.searchDate = "2020-1-4";
        this.algorithm = new Algorithm();

        PostEntity p1 = new PostEntity(1, "ietssssss ", PostKind.TWEET);
        p1.setDate("2000-1-1");
        allPosts.add(p1);
        PostEntity p2 = new PostEntity(1, "ietssssss ", PostKind.TWEET);
        p2.setDate("2020-1-1");
        allPosts.add(p2);
        PostEntity p3 = new PostEntity(1, "ietssssss ", PostKind.TWEET);
        p3.setDate("2020-1-2");
        allPosts.add(p3);
        PostEntity p4 = new PostEntity(1, "ietssssss ", PostKind.TWEET);
        p4.setDate("2020-1-2");
        allPosts.add(p4);
        PostEntity p5 = new PostEntity(1, "ietssssss ", PostKind.TWEET);
        p5.setDate("2020-1-3");
        allPosts.add(p5);
        PostEntity p6 = new PostEntity(1, "ietssssss ", PostKind.TWEET);
        p6.setDate("2020-1-5");
        allPosts.add(p6);
        PostEntity p7 = new PostEntity(1, "ietssssss ", PostKind.TWEET);
        p7.setDate("2020-1-6");
        allPosts.add(p7);
    }

    @AfterEach
    void tearDown() {
        this.allPosts = null;
        this.searchDate = null;
        this.algorithm = null;
    }

    @Test
    void splitDate() {
        //Arrange
        List<Integer> dates = new ArrayList<>();

        //Act
        dates = algorithm.splitDate(this.searchDate);

        //Assert
        assertEquals(2020, dates.get(0));
        assertEquals(1, dates.get(1));
        assertEquals(4, dates.get(2));
    }

    @Test
    void getMiddleListItemEven() {
        //Arrange
        PostEntity p8 = new PostEntity(1, "ietssssss ", PostKind.TWEET);

        //Act
        p8.setDate("2020-1-12");
        this.allPosts.add(p8);
        PostEntity middleItem = algorithm.getMiddleListItem(allPosts);

        //Assert
        assertEquals(allPosts.get(4), middleItem);
    }

    @Test
    void getMiddleListItemUneven() {
        //Act
        PostEntity middleItem = algorithm.getMiddleListItem(allPosts);

        //Assert
        assertEquals(allPosts.get(3), middleItem);
        assertNotEquals(allPosts.get(4), middleItem);
    }

    @Test
    void splitList() {
        //Arrange
        int position = 3; //List is uneven so 3 is the middle item in the list;
        int result,searchelement, compareElement;
        String date;
        List<PostEntity> posts = new ArrayList<>();

        //Act
        searchelement = algorithm.splitDate(searchDate).get(2);
        date = this.allPosts.get(3).getDate();
        compareElement =  algorithm.splitDate(date).get(2);
        result = algorithm.compare(searchelement,compareElement);
        posts = algorithm.splitList(this.allPosts,position,result);

        //Assert
        assertNotNull(posts);
        assertEquals(4, posts.size());
    }

    @Test
    void getPossiblePostsDateNotFound() {
        //Arrange
        int searchElement;
        List<PostEntity> postEntities = new ArrayList<>();

        //Act
        searchElement = algorithm.splitDate(this.searchDate).get(2);
        postEntities = algorithm.getPossiblePosts(this.allPosts, searchElement);

        //Assert
        assertNotNull(postEntities);
        assertEquals(0, postEntities.size());
    }

    @Test
    void getPossiblePostsDateFound() {
        //Arrange
        int searchElement;
        List<PostEntity> postEntities = new ArrayList<>();

        //Act
        this.searchDate = "2020-1-3";
        searchElement = algorithm.splitDate(this.searchDate).get(0);
        postEntities = algorithm.getPossiblePosts(this.allPosts, searchElement);

        //Assert
        assertNotNull(postEntities);

        assertEquals(6, postEntities.size());
    }

    @Test
    void getPossiblePostsDateMultipleFound() {
        //Arrange
        int searchElement;
        List<PostEntity> postEntities = new ArrayList<>();

        //Act
        this.searchDate = "2020-1-2";
        searchElement = algorithm.splitDate(this.searchDate).get(0);
        postEntities = algorithm.getPossiblePosts(this.allPosts, searchElement);

        //Assert
        assertEquals(6, postEntities.size());
    }

    @Test
    void BinarySearch(){
        //Arrange
        List<PostEntity> posts;

        //Act
        this.searchDate = "2020-1-2";
        posts = algorithm.BinarySearch(this.allPosts, this.searchDate);

        //Assert
        assertEquals(2, posts.size());
    }
}