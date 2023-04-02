package com.example.dronesv2.service;

import com.example.dronesv2.dto.MedicationDTO;
import com.example.dronesv2.model.Medication;
import java.util.List;


public interface MedicationService {
    Medication saveMedication (MedicationDTO medication) throws Exception;
    Medication getMedication (String medicationCode) throws Exception;
    Medication updateMedication (MedicationDTO medication) throws Exception;
    void deleteMedication (String medicationCode) throws Exception;
    List<Medication> getAllMedications();
}
