package com.bigidea.twitter.rest.Specifications;

import com.bigidea.twitter.rest.Entities.HashTagEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class HashTagSpecifications {

    //Dit is een query voor het zoeken in de db op basis van de naam van een hashtag entiteit
    public static Specification<HashTagEntity>nameLike(String name){
        return (entity,cq,cb) -> Optional.ofNullable(name).map(n -> cb.like(entity.get("name"),"%" + n + "%")).orElse(null);
    }
}
