package com.example.dronesv2.controller;


import com.example.dronesv2.dto.DroneDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/drones")
public class DroneController {

    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity<?> registerDrone(@RequestBody DroneDTO drone) throws Exception {
        try{
            Drone registeredDrone = droneService.saveDrone(drone);
            return ResponseEntity.ok(registeredDrone);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{medicationCode}/{droneSerialNumber}/add-medication")
    public ResponseEntity<?> addMedicationToDrone(@PathVariable String medicationCode,@PathVariable String droneSerialNumber) throws Exception {
        try{
            Drone drone = droneService.addMedicationToDrone(medicationCode, droneSerialNumber);
            return ResponseEntity.ok(drone);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


/*
    @PutMapping("/{serialNumber}/load-medications")
    public ResponseEntity<?> loadMedications(@PathVariable String serialNumber,
                                             @RequestBody List<Medication> medications) throws DroneBatteryLowException, DroneNotFoundException, DroneCapacityExceededException {
        Drone loadedDrone = droneService.loadDrone(serialNumber, medications);
        return ResponseEntity.ok(loadedDrone);
    }

    @GetMapping("/{serialNumber}/loaded-medications")
    public ResponseEntity<?> getLoadedMedications(@PathVariable String serialNumber) throws DroneNotFoundException {
        List<Medication> loadedMedications = droneService.getLoadedMedications(serialNumber);
        return ResponseEntity.ok(loadedMedications);
    }

    @GetMapping("/available-for-loading")
    public ResponseEntity<?> getAvailableDronesForLoading() {
        List<Drone> availableDrones = droneService.getDronesByState(DroneState.LOADED);
        return ResponseEntity.ok(availableDrones);
    }

    @GetMapping("/{serialNumber}/battery-level")
    public ResponseEntity<?> getDroneBatteryLevel(@PathVariable String serialNumber) throws DroneNotFoundException {
        double batteryLevel = droneService.getDroneBatteryLevel(serialNumber);
        return ResponseEntity.ok(batteryLevel);
    }*/
}