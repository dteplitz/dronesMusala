package com.example.dronesv2.service;

import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneModel;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    //private final MedicationRepository medicationRepository;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository/*, MedicationRepository medicationRepository*/) {
        this.droneRepository = droneRepository;
//        this.medicationRepository = medicationRepository;
    }

    @Override
    public Drone registerDrone(String serialNumber, DroneModel model, int weightLimit, int batteryCapacity) {
        // Implement the logic for registering a drone here
        Drone drone = new Drone(serialNumber, model, weightLimit, batteryCapacity,DroneState.IDLE/*, new ArrayList<Medication>()*/);
        return droneRepository.save(drone);
    }

    @Override
    public Drone loadDrone(String serialNumber/*, List<Medication> medications*/) throws Exception {
        Drone drone = droneRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new Exception("Drone not found with serialNumber: " + serialNumber));

        if (drone.getState() != DroneState.IDLE) {
            throw new IllegalStateException("Drone is not in IDLE state, it cannot be loaded.");
        }

        /*double totalWeight = medications.stream().mapToDouble(Medication::getWeight).sum();
        if (totalWeight > drone.getWeightLimit()) {
            throw new DroneCapacityExceededException("Drone cannot be loaded with total weight more than " + drone.getWeightLimit() + "g.");
        }

        int batteryLevel = drone.getBatteryCapacity();
        if (batteryLevel < 25) {
            throw new DroneBatteryLowException("Drone battery level is " + batteryLevel + "%. It should be charged before loading.");
        }

        drone.addMedications(medications);*/
        drone.setState(DroneState.LOADING);
        return saveDrone(drone);
    }

    /*@Override
    public List<Medication> getLoadedMedications(String serialNumber) throws DroneNotFoundException {
        Drone drone = droneRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new DroneNotFoundException("Drone not found with serialNumber: " + serialNumber));

        if (drone.getState() != DroneState.LOADING && drone.getState() != DroneState.LOADED) {
            throw new IllegalStateException("Drone is not in LOADING or LOADED state, it cannot have loaded medications.");
        }

        return drone.getMedications();
    }*/

    @Override
    public List<Drone> getAvailableDrones() {
        return droneRepository.findByState(DroneState.IDLE);
    }

    @Override
    public double getDroneBatteryLevel(String serialNumber) throws Exception {
        Drone drone = droneRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new Exception("Drone not found with serialNumber: " + serialNumber));

        return drone.getBatteryCapacity();
    }

    @Override
    public Drone getDroneBySerialNumber(String droneSerialNumber) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(droneSerialNumber);
        if (optionalDrone.isPresent()) {
            return optionalDrone.get();
        } else {
            throw new Exception("Drone not found with serial number: " + droneSerialNumber);
        }
    }

    @Override
    public Drone saveDrone (Drone drone){
        drone.setState(DroneState.LOADED);
        return droneRepository.save(drone);
    }

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public void deleteDrone(String droneSerialNumber) {
        droneRepository.deleteBySerialNumber(droneSerialNumber);
    }

    @Override
    public List<Drone> getDronesByState(DroneState droneState){
        return droneRepository.findByState(droneState);
    }


}