package com.oliinyk.practice.touragencyserver.entity;

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
@Table(name = "Feeding")
public class Feeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "feeding_type_id", nullable = false)
    private FeedingType feedingType;

    @Column(name = "price_per_adult", nullable = false)
    private int pricePerAdult;

    @Column(name = "price_per_child", nullable = false)
    private int pricePerChild;

    public Feeding(Hotel hotel, FeedingType feedingType, int pricePerAdult, int pricePerChild) {
        this.hotel = hotel;
        this.feedingType = feedingType;
        this.pricePerAdult = pricePerAdult;
        this.pricePerChild = pricePerChild;
    }
}
