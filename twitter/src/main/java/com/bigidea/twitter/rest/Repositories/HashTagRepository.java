package com.bigidea.twitter.rest.Repositories;

import com.bigidea.twitter.rest.Entities.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Integer>, JpaSpecificationExecutor<HashTagEntity> {
    @Query(value = "select * from hashtags where name LIKE :name", nativeQuery = true)
    HashTagEntity getHashTagEntityByName(String name);
}
