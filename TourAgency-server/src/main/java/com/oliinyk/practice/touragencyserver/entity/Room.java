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

    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(name = "price_per_adult", nullable = false)
    private int pricePerAdult;

    @Column(name = "price_per_child", nullable = false)
    private int pricePerChild;

    public Room(Hotel hotel, RoomType roomType, int pricePerAdult, int pricePerChild) {
        this.hotel = hotel;
        this.roomType = roomType;
        this.pricePerAdult = pricePerAdult;
        this.pricePerChild = pricePerChild;
    }
}
