package com.oliinyk.practice.touragencyserver.service;

import com.oliinyk.practice.touragencyserver.entity.Tour;
import com.oliinyk.practice.touragencyserver.response.TourResponse;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TourService {
    Tour save(Tour tour);
    List<Tour> findAll();
    List<TourResponse> findAllWithPrices(String name, int nightsFrom, int nightsTo, Date dateFrom, Date dateTo, String city, int adults, int children);
    Optional<Tour> findById(int id);
    void deleteById(int id);
}
