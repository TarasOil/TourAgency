package com.oliinyk.practice.touragencyserver.entity;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "City")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Hidden
    @OneToMany(mappedBy = "departureCity")
    private List<Tour> tours;

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
    }
}
