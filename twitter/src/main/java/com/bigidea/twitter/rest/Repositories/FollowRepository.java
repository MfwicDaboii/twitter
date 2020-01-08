package com.bigidea.twitter.rest.Repositories;

import com.bigidea.twitter.rest.Entities.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Integer> {
    @Query(value = "select * from follow where user_id=?1", nativeQuery = true)
    ArrayList<FollowEntity> getAllFollowers(int id);
    @Query(value = "select * from follow where follower_id=?1", nativeQuery = true)
    ArrayList<FollowEntity> getAllFollowing(int id);

    @Transactional
    @Modifying
    @Query(value = "delete from follow where follower_id=:follower_id AND user_id=:user_id", nativeQuery = true)
    int unfollow( int user_id, int follower_id);
}
