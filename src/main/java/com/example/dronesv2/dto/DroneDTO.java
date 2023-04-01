package com.example.dronesv2.dto;
import com.example.dronesv2.model.DroneModel;
import com.example.dronesv2.model.DroneState;

import javax.validation.constraints.NotNull;

public class DroneDTO {
    private Long id;

    @NotNull
    private String serialNumber;
    @NotNull
    private DroneModel model;
    @NotNull
    private int weightLimit;
    @NotNull
    private int batteryCapacity;
    @NotNull
    private DroneState state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public @NotNull String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public @NotNull int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public @NotNull int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public @NotNull DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public @NotNull DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}

