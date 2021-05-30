package com.oliinyk.practice.touragencyserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "Tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "nights", columnDefinition = "TINYINT", nullable = false)
    private int nights;

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
}
