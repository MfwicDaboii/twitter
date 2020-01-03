package com.bigidea.twitter.rest.Repositories;

import com.bigidea.twitter.rest.Entities.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
}
