package com.oliinyk.practice.touragencyserver.repository;

import com.oliinyk.practice.touragencyserver.entity.Feeding;
import com.oliinyk.practice.touragencyserver.entity.FeedingType;
import com.oliinyk.practice.touragencyserver.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedingRepository extends JpaRepository<Feeding, Integer> {
    Optional<Feeding> findByHotelAndFeedingType(Hotel hotel, FeedingType feedingType);
}
