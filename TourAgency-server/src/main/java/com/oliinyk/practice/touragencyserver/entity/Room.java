package com.oliinyk.practice.touragencyserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(name = "price_per_adult", scale = 7, precision = 2, nullable = false)
    private Double pricePerAdult;

    @Column(name = "price_per_child", scale = 7, precision = 2, nullable = false)
    private Double pricePerChild;

    public Room(Hotel hotel, RoomType roomType, Double pricePerAdult, Double pricePerChild) {
        this.hotel = hotel;
        this.roomType = roomType;
        this.pricePerAdult = pricePerAdult;
        this.pricePerChild = pricePerChild;
    }
}
