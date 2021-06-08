package com.oliinyk.practice.touragencyserver.repository;

import com.oliinyk.practice.touragencyserver.entity.HotelService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelServiceRepository extends JpaRepository<HotelService, Integer> {
}
