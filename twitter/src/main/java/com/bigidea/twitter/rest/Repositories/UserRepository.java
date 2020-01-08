package com.bigidea.twitter.rest.Repositories;

import com.bigidea.twitter.rest.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(value = "select * from user_profiles where id=?1" ,nativeQuery = true)
    UserEntity getById(int id);
}
