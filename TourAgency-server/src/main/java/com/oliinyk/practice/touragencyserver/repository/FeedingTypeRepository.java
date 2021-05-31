package com.oliinyk.practice.touragencyserver.repository;

import com.oliinyk.practice.touragencyserver.entity.FeedingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedingTypeRepository extends JpaRepository<FeedingType, Integer> {
}
