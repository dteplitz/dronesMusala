package com.example.dronesv2.controller;


import com.example.dronesv2.dto.DroneDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {

    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity<?> registerDrone(@RequestBody DroneDTO drone) {
        try{
            Drone registeredDrone = droneService.saveDrone(drone);
            return ResponseEntity.ok(registeredDrone);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/{medicationCode}/{droneSerialNumber}/add-medication")
    public ResponseEntity<?> addMedicationToDrone(@PathVariable String medicationCode,@PathVariable String droneSerialNumber) {
        try{
            Drone drone = droneService.addMedicationToDrone(medicationCode, droneSerialNumber);
            return ResponseEntity.ok(drone);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/{serialNumber}/loaded-medications")
    public ResponseEntity<?> getLoadedMedications(@PathVariable String serialNumber) {
        try{
            List<Medication> loadedMedications = droneService.getLoadedMedications(serialNumber);
            return ResponseEntity.ok(loadedMedications);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/available-for-loading")
    public ResponseEntity<?> getAvailableDronesForLoading() {
        try{
            List<Drone> availableDrones = droneService.getDronesByState(DroneState.IDLE);
            return ResponseEntity.ok(availableDrones);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/{serialNumber}/battery-level")
    public ResponseEntity<?> getDroneBatteryLevel(@PathVariable String serialNumber) {
        try{
            int batteryLevel = droneService.getDroneBatteryLevel(serialNumber);
            return ResponseEntity.ok("Battery level is "+batteryLevel+"%");
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    //ToDo: change state when loading, set state of drone, another one to lower battery and charge battery
    //put de drones y medicaciones
}