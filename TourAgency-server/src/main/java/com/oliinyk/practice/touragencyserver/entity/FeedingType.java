package com.oliinyk.practice.touragencyserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="FeedingType")
public class FeedingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, unique = true, nullable = false)
    private String name;

    @Column(length = 10, unique = true, nullable = false)
    private String abbr;

    public FeedingType(String name, String abbr) {
        this.name = name;
        this.abbr = abbr;
    }
}
