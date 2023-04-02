package com.example.dronesv2.service;

import com.example.dronesv2.dto.DroneDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneModel;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;

import java.util.List;

public interface DroneService {

    Drone registerDrone(String serialNumber, DroneModel model, int weightLimit, int batteryCapacity);

    Drone loadDrone(String serialNumber/*, List<Medication> medications*/) throws Exception;

    //List<Medication> getLoadedMedications(String serialNumber) throws Exception;

    List<Drone> getAvailableDrones();

    int getDroneBatteryLevel(String serialNumber) throws Exception;

    Drone getDroneBySerialNumber(String droneSerialNumber) throws Exception;

    List<Drone> getDronesByState(DroneState droneState);

    Drone saveDrone (DroneDTO drone) throws Exception;

    Drone saveDrone (Drone drone) throws Exception;

    List<Drone> getAllDrones();

    void deleteDrone(String droneSerialNumber);

    Drone addMedicationToDrone(String medicationCode, String droneSerialNumber) throws Exception;

    List<Medication> getLoadedMedications(String serialNumber) throws Exception;
}