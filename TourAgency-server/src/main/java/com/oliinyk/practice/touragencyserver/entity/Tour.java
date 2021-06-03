package com.oliinyk.practice.touragencyserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "nights", columnDefinition = "TINYINT", nullable = false)
    private int nights;

    @Column(name = "base_price", nullable = false)
    private int basePrice;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City departureCity;

    @Column(name = "departure_date", nullable = false)
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "feeding_type_id", nullable = false)
    private FeedingType feedingType;

    public Tour(Hotel hotel, int nights, int basePrice, City departureCity, Date departureDate, RoomType roomType, FeedingType feedingType) {
        this.hotel = hotel;
        this.nights = nights;
        this.basePrice = basePrice;
        this.departureCity = departureCity;
        this.departureDate = departureDate;
        this.roomType = roomType;
        this.feedingType = feedingType;
    }
}
