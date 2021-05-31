package com.oliinyk.practice.touragencyserver.repository;

import com.oliinyk.practice.touragencyserver.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Integer> {
}
