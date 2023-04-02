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
    @Column(nullable = false, unique=true)
    private String name;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false, unique=true)
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
    public void setName(String name) {
        this.name = name;
    }


    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}