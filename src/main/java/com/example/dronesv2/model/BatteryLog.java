package com.example.dronesv2.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "batteryLog")
public class BatteryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String droneSerialNumber;
    @Column(nullable = false)
    private int batteryPercentage;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public BatteryLog() {}

    public BatteryLog(String droneSerialNumber, int batteryPercentage) {
        this.droneSerialNumber = droneSerialNumber;
        this.batteryPercentage = batteryPercentage;
        this.timestamp = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDroneSerialNumber() {
        return droneSerialNumber;
    }

    public void setDroneSerialNumber(String droneSerialNumber) {
        this.droneSerialNumber = droneSerialNumber;
    }

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}