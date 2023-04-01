package com.example.dronesv2.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private double weight;
    @Column
    private String code;
    @Column
    private String image;
    /*@ManyToMany(mappedBy = "medications")
    private List<Drone> drones;*/

    public Medication(String name, double weight, String code, String image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    public Medication() {

    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getCode() {
        return code;
    }

    public String getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // other methods
}