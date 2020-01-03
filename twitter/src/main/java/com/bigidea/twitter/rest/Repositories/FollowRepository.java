package com.bigidea.twitter.rest.Repositories;

import com.bigidea.twitter.rest.Entities.FollowEntity;
import com.bigidea.twitter.rest.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Integer> {
    @Query(value = "select * from follow where user_id=?1", nativeQuery = true)
    ArrayList<Integer> getAllByUserID(int id);

}
