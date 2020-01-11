package com.bigidea.twitter.rest.Specifications;

import com.bigidea.twitter.rest.Entities.PostEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class PostSpecifications {
    public static Specification<PostEntity> dateLike(String date){
        return (entity,cq,cb) -> Optional.ofNullable(date).map(n -> cb.equal(entity.get("name"),n)).orElse(null);
    }
}
