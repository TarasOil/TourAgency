package com.oliinyk.practice.touragencyserver.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, unique = true, nullable = false)
    private String name;

    @Column(name = "star_category", columnDefinition = "TINYINT", nullable = false)
    private int starCategory;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Hidden
    @OneToMany(mappedBy = "hotel")
    private List<Tour> tours;

    @Hidden
    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @Hidden
    @OneToMany(mappedBy = "hotel")
    private List<Image> images;

    public Hotel(String name, int starCategory, City city) {
        this.name = name;
        this.starCategory = starCategory;
        this.city = city;
    }
}
