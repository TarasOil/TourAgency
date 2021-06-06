package com.oliinyk.practice.touragencyserver.service.impl;

import com.oliinyk.practice.touragencyserver.entity.Feeding;
import com.oliinyk.practice.touragencyserver.entity.Room;
import com.oliinyk.practice.touragencyserver.entity.Tour;
import com.oliinyk.practice.touragencyserver.repository.*;
import com.oliinyk.practice.touragencyserver.response.TourResponse;
import com.oliinyk.practice.touragencyserver.service.TourService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final FeedingRepository feedingRepository;

    public TourServiceImpl(TourRepository tourRepository, CountryRepository countryRepository, CityRepository cityRepository,
                           HotelRepository hotelRepository, RoomRepository roomRepository, FeedingRepository feedingRepository) {
        this.tourRepository = tourRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.feedingRepository = feedingRepository;
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public List<TourResponse> findAllWithPrices(String name, int nightsFrom, int nightsTo, Date dateFrom, Date dateTo, String city, int adults, int children) {
        List<Tour> tours = null;
        if(countryRepository.existsByNameContains(name)) {
            tours = tourRepository.findAllByHotelCityCountryAndNightsBetweenAndDepartureDateBetweenAndDepartureCity(
                    countryRepository.findByNameContains(name).get(), nightsFrom, nightsTo, dateFrom, dateTo,
                    cityRepository.findByNameContains(city).get());
        } else if(cityRepository.existsByNameContains(name)) {
            tours = tourRepository.findAllByHotelCityAndNightsBetweenAndDepartureDateBetweenAndDepartureCity(
                    cityRepository.findByNameContains(name).get(), nightsFrom, nightsTo, dateFrom, dateTo,
                    cityRepository.findByNameContains(city).get());
        } else if(hotelRepository.existsByNameContains(name)) {
            tours = tourRepository.findAllByHotelAndNightsBetweenAndDepartureDateBetweenAndDepartureCity(
                    hotelRepository.findByNameContains(name).get(), nightsFrom, nightsTo, dateFrom, dateTo,
                    cityRepository.findByNameContains(city).get());
        }

        List<TourResponse> tourResponses = new ArrayList<>();
        if(tours != null) {
            for (Tour tour : tours) {
                TourResponse tourResponse = new TourResponse(tour);
                Room room = roomRepository.findByHotelAndRoomType(tour.getHotel(), tour.getRoomType()).get();
                Feeding feeding = feedingRepository.findByHotelAndFeedingType(tour.getHotel(), tour.getFeedingType()).get();
                tourResponse.setPrice((int)Math.ceil(tour.getBasePrice()
                        + adults*(room.getPricePerAdult()*tour.getNights()+feeding.getPricePerAdult()*tour.getNights())
                        + children*(room.getPricePerChild()*tour.getNights()+feeding.getPricePerChild()*tour.getNights())));
                tourResponses.add(tourResponse);
            }
        }

        return tourResponses;
    }

    @Override
    public Optional<Tour> findById(int id) {
        return tourRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        tourRepository.deleteById(id);
    }
}
