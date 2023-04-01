package com.example.dronesv2.controller;

import com.example.dronesv2.dto.MedicationDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.service.DroneService;
import com.example.dronesv2.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medications")
public class MedicationController {
    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @PostMapping
    public ResponseEntity<?> registerMedication(@RequestBody MedicationDTO medication) {
        try{
            Medication registeredMedication = medicationService.saveMedication(medication);
            return ResponseEntity.ok(registeredMedication);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
