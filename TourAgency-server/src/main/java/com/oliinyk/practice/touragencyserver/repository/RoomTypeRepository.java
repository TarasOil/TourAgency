package com.oliinyk.practice.touragencyserver.repository;

import com.oliinyk.practice.touragencyserver.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
}
