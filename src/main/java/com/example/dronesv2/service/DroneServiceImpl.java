package com.example.dronesv2.service;

import com.example.dronesv2.dto.DroneDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.repository.JpaDroneRepository;
import com.example.dronesv2.repository.JpaMedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Drone> getAvailableDrones() {
        return droneRepository.findByState(DroneState.IDLE);
    }

    @Override
    public int getDroneBatteryLevel(String serialNumber) throws Exception {
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

    @Transactional
    @Override
    public Drone saveDrone (DroneDTO droneDTO) throws Exception {
        Optional<Drone> droneActual = droneRepository.findBySerialNumber(droneDTO.getSerialNumber());
        if (droneActual.isPresent()) {
            throw new Exception("Drone already created");
        }
        Drone drone = new Drone(droneDTO.getSerialNumber(),droneDTO.getModel(),droneDTO.getWeightLimit(),droneDTO.getBatteryCapacity(),DroneState.IDLE);
        checkDroneIsValid(drone);
        return droneRepository.save(drone);
    }

    @Transactional
    @Override
    public void deleteDrone(String droneSerialNumber) {
        droneRepository.deleteBySerialNumber(droneSerialNumber);
    }

    @Transactional
    @Override
    public Drone updateDrone(DroneDTO droneDTO) throws Exception {
        Optional<Drone> droneActual = droneRepository.findBySerialNumber(droneDTO.getSerialNumber());
        if (!droneActual.isPresent()) {
            throw new Exception("Drone not found");
        }
        Drone drone = droneActual.get();
        drone.setState(droneDTO.getState());
        drone.setModel(droneDTO.getModel());
        drone.setWeightLimit(droneDTO.getWeightLimit());
        drone.setBatteryCapacity(droneDTO.getBatteryCapacity());

        checkDroneIsValid(drone);
        return droneRepository.update(drone);
    }

    @Transactional
    @Override
    public Drone addMedicationToDrone(String medicationCode, String droneSerialNumber) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(droneSerialNumber);
        if (optionalDrone.isPresent()) {
            Optional<Medication> optionalMedication = medicationRepository.findByCode(medicationCode);
            if (optionalMedication.isPresent()) {
                if(!checkCanAddMedication(optionalDrone.get(),optionalMedication.get())){
                    throw new Exception("Drone cannot carry that more weight");
                }
                if(optionalDrone.get().getState() != DroneState.LOADING){
                    throw new Exception("Cannot add meditacion if status isnt LOADING");
                }
                optionalDrone.get().addMedication(optionalMedication.get());
                return droneRepository.update(optionalDrone.get());
            }
            else {
                throw new Exception("Medication not found with code: " + medicationCode);
            }
        } else {
            throw new Exception("Drone not found with serial number: " + droneSerialNumber);
        }
    }

    private boolean checkCanAddMedication(Drone drone, Medication medication) {
        return drone.getWeightLimit() > drone.getActualWeight() + medication.getWeight();
    }

    @Override
    public List<Medication> getLoadedMedications(String serialNumber) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(serialNumber);
        if (optionalDrone.isPresent()) {
            return optionalDrone.get().getMedications();
        } else {
            throw new Exception("Drone not found with serial number: " + serialNumber);
        }
    }

    @Transactional
    @Override
    public Drone updateDroneState(String droneSerialNumber, DroneState newState) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(droneSerialNumber);
        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();
            if (newState == DroneState.LOADING && drone.getBatteryCapacity() < 25){
                throw new Exception("Cannot set to Loading if battery is below 25%");
            }
            return drone;
        } else {
            throw new Exception("Drone not found with serial number: " + droneSerialNumber);
        }
    }

    @Transactional
    @Override
    public void deleteMedicationFromDrone(String serialNumber, String medicationCode) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(serialNumber);
        Optional<Medication> optionalMedication = medicationRepository.findByCode((medicationCode));
        if (!optionalDrone.isPresent()) {
            throw new Exception("Drone not found");
        }
        if (!optionalMedication.isPresent()) {
            throw new Exception("Drone not found");
        }
        Drone drone = optionalDrone.get();
        drone.removeMedication(optionalMedication.get());
        droneRepository.update(drone);
    }

    @Transactional
    @Override
    public void deleteAllMedicationFromDrone(String serialNumber) throws Exception {
        Optional<Drone> optionalDrone = droneRepository.findBySerialNumber(serialNumber);
        if (!optionalDrone.isPresent()) {
            throw new Exception("Drone not found");
        }
        Drone drone = optionalDrone.get();
        for(Medication medication: drone.getMedications()){
            drone.removeMedication(medication);
        }
        droneRepository.update(drone);
    }

    @Override
    public List<Drone> getDronesByState(DroneState droneState){
        return droneRepository.findByState(droneState);
    }

    private void checkDroneIsValid(Drone drone) throws Exception {
        if(!checkValidDroneSerialNumber(drone.getSerialNumber())){
            throw new Exception("Serial number cannot be more than 100 characters");
        }
        if(!checkValidWeight(drone.getWeightLimit())){
            throw new Exception("Weight can not be bigger than 500gr");
        }
        if(!checkValidBatteryCapacity(drone.getBatteryCapacity())){
            throw new Exception("Battery capacity can not be bigger than 100%");
        }
    }

    private boolean checkValidBatteryCapacity(int batteryCapacity) {
        return batteryCapacity < 100;
    }

    private boolean checkValidWeight(int weight) {
        return weight < 500;
    }
    private boolean checkValidDroneSerialNumber(String serialNumber) throws Exception {
        return serialNumber.length() < 100;
    }


}