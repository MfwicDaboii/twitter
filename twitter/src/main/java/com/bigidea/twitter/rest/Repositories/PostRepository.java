package com.bigidea.twitter.rest.Repositories;

import com.bigidea.twitter.enumerations.PostKind;
import com.bigidea.twitter.rest.Entities.PostEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> getPostsByDate(int id);
    PostEntity getById(int id);
    @Query(value = "select * from posts where user_id=?1" ,nativeQuery = true)
    List<PostEntity> getActivityByUserID(int id);
}

