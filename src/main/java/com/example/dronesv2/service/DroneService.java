package com.example.dronesv2.service;

import com.example.dronesv2.dto.DroneDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;

import java.util.List;

public interface DroneService {

    List<Drone> getAvailableDrones();

    int getDroneBatteryLevel(String serialNumber) throws Exception;

    Drone getDroneBySerialNumber(String droneSerialNumber) throws Exception;

    List<Drone> getDronesByState(DroneState droneState);

    Drone saveDrone (DroneDTO drone) throws Exception;

    void deleteDrone(String droneSerialNumber);

    Drone updateDrone(DroneDTO drone) throws Exception;

    Drone addMedicationToDrone(String medicationCode, String droneSerialNumber) throws Exception;

    List<Medication> getLoadedMedications(String serialNumber) throws Exception;

    Drone updateDroneState(String droneSerialNumber, DroneState newState) throws Exception;
}