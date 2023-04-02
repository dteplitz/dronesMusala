package com.example.dronesv2.controller;

import com.example.dronesv2.dto.MedicationDTO;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/medications", produces = "application/json")
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
    @GetMapping
    public ResponseEntity<?> getMedicationByCode(@PathVariable String medicationCode) {
        try{
            Medication registeredMedication = medicationService.getMedication(medicationCode);
            return ResponseEntity.ok(registeredMedication);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> updateMedication(@RequestBody MedicationDTO medication) {
        try{
            Medication registeredMedication = medicationService.updateMedication(medication);
            return ResponseEntity.ok(registeredMedication);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/{medicationCode}")
    public ResponseEntity<?> deleteMedication(@PathVariable String medicationCode) {
        try{
            medicationService.deleteMedication(medicationCode);
            return ResponseEntity.ok("medication deleted");
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllMedication() {
        try{
            List<Medication> medications = medicationService.getAllMedications();
            return ResponseEntity.ok(medications);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
