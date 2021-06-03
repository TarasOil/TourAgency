package com.oliinyk.practice.touragencyserver.response;

import com.oliinyk.practice.touragencyserver.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TourResponse {
    private int id;
    private Hotel hotel;
    private int nights;
    private City departureCity;
    private Date departureDate;
    private RoomType roomType;
    private FeedingType feedingType;
    private int price;

    public TourResponse(Tour tour) {
        this.id = id;
        this.hotel = tour.getHotel();
        this.nights = tour.getNights();
        this.departureCity = tour.getDepartureCity();
        this.departureDate = tour.getDepartureDate();
        this.roomType = tour.getRoomType();
        this.feedingType = tour.getFeedingType();
    }
}
