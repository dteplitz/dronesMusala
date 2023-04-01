package com.example.dronesv2.dto;

import com.example.dronesv2.model.DroneModel;
import com.example.dronesv2.model.DroneState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MedicationDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private double weight;
    @NotNull
    private String code;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public @NotNull String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @NotNull double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public @NotNull String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
