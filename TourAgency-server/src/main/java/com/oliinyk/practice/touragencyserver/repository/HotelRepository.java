package com.oliinyk.practice.touragencyserver.repository;

import com.oliinyk.practice.touragencyserver.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
