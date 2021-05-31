package com.oliinyk.practice.touragencyserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "image_url", unique = true, nullable = false)
    private String imageUrl;

    public Image(Hotel hotel, String imageUrl) {
        this.hotel = hotel;
        this.imageUrl = imageUrl;
    }
}
