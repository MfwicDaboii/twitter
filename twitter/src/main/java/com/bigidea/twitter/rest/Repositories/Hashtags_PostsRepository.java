package com.bigidea.twitter.rest.Repositories;

import com.bigidea.twitter.rest.Entities.Hashtags_PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Hashtags_PostsRepository extends JpaRepository<Hashtags_PostsEntity, Integer> {
    @Query(value = "select post_id from hashtags_post where tag_id=:id", nativeQuery = true)
    List<Integer> getAllPostIdsByTagId(int id);
}
