package com.example.dronesv2.service;

import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneModel;
import com.example.dronesv2.model.DroneState;

import java.util.List;

public interface DroneService {

    Drone registerDrone(String serialNumber, DroneModel model, int weightLimit, int batteryCapacity);

    Drone loadDrone(String serialNumber/*, List<Medication> medications*/) throws Exception;

    //List<Medication> getLoadedMedications(String serialNumber) throws Exception;

    List<Drone> getAvailableDrones();

    double getDroneBatteryLevel(String serialNumber) throws Exception;

    Drone getDroneBySerialNumber(String droneSerialNumber) throws Exception;

    List<Drone> getDronesByState(DroneState droneState);

    Drone saveDrone (Drone drone);

    List<Drone> getAllDrones();

    void deleteDrone(String droneSerialNumber);

}