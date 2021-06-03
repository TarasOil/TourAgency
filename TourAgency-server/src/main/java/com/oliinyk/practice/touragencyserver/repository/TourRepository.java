package com.oliinyk.practice.touragencyserver.repository;

import com.oliinyk.practice.touragencyserver.entity.City;
import com.oliinyk.practice.touragencyserver.entity.Country;
import com.oliinyk.practice.touragencyserver.entity.Hotel;
import com.oliinyk.practice.touragencyserver.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {
    List<Tour> findAllByHotelCityCountryAndNightsBetweenAndDepartureDateBetweenAndDepartureCity(Country country, int nightFrom, int nightTo, Date dateFrom, Date dateTo, City city);
    List<Tour> findAllByHotelCityAndNightsBetweenAndDepartureDateBetweenAndDepartureCity(City City, int nightFrom, int nightTo, Date dateFrom, Date dateTo, City city);
    List<Tour> findAllByHotelAndNightsBetweenAndDepartureDateBetweenAndDepartureCity(Hotel hotel, int nightFrom, int nightTo, Date dateFrom, Date dateTo, City city);
}
