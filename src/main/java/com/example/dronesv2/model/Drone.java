package com.example.dronesv2.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "drone")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique=true)
    private String serialNumber;
    @Column(nullable = false)
    private DroneModel model;
    @Column(nullable = false)
    private int weightLimit;
    @Column(nullable = false)
    private int batteryCapacity;
    @Column(nullable = false)
    private DroneState state;
    @ManyToMany()
    @JoinTable(
            name = "drones_medications",
            joinColumns = @JoinColumn(name = "drone_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications;

    public Drone(String serialNumber, DroneModel model, int weightLimit, int batteryCapacity, DroneState state/*, List<Medication> medications*/) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.medications = medications;
    }

    public Drone() {

    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    public boolean isBatteryLevelBelowThreshold() {
        return batteryCapacity < 25;
    }

    public void addMedication(Medication medication) throws Exception {
        if (medications.size() >= getWeightLimit()) {
            throw new Exception("Drone capacity exceeded");
        }
        medications.add(medication);
    }

    public void addMedications(List <Medication> newMedications) {
        medications.addAll(newMedications);
    }

    public void removeMedication(Medication medication) {
        medications.remove(medication);
    }

    public List<Medication> getMedications() {
        return medications;
    }


    public double getActualWeight() {
        if(medications == null)
            return 0;
        return medications.stream()
                .mapToDouble(m -> m.getWeight())
                .sum();
    }
}
