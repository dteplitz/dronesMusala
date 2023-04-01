package com.example.dronesv2.service;

import com.example.dronesv2.dto.DroneDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneModel;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.repository.JpaDroneRepository;
import com.example.dronesv2.repository.JpaMedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService {

    private final JpaDroneRepository droneRepository;
    private final JpaMedicationRepository medicationRepository;

    @Autowired
    public DroneServiceImpl(JpaDroneRepository droneRepository, JpaMedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
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
    public Drone saveDrone (Drone drone) throws Exception {
        Optional<Drone> droneActual = droneRepository.findBySerialNumber(drone.getSerialNumber());
        if (droneActual.isPresent()) {
            throw new Exception("Drone already created");
        }
        drone.setState(DroneState.LOADED);
        return droneRepository.save(drone);
    }

    @Override
    public Drone saveDrone (DroneDTO drone) throws Exception {
        Optional<Drone> droneActual = droneRepository.findBySerialNumber(drone.getSerialNumber());
        if (droneActual.isPresent()) {
            throw new Exception("Drone already created");
        }
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
    public Drone addMedicationToDrone(String medicationCode, String droneSerialNumber) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(droneSerialNumber);
        if (optionalDrone.isPresent()) {
            Optional<Medication> optionalMedication = medicationRepository.findByCode(medicationCode);
            if (optionalMedication.isPresent()) {
                optionalDrone.get().addMedication(optionalMedication.get());
                return optionalDrone.get();
            }
            else {
                throw new Exception("Medication not found with code: " + medicationCode);
            }
        } else {
            throw new Exception("Drone not found with serial number: " + droneSerialNumber);
        }
    }

    @Override
    public List<Drone> getDronesByState(DroneState droneState){
        return droneRepository.findByState(droneState);
    }


}