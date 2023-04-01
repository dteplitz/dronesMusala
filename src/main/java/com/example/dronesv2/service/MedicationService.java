package com.example.dronesv2.service;

import com.example.dronesv2.dto.MedicationDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneModel;
import com.example.dronesv2.model.Medication;

public interface MedicationService {
    Medication saveMedication (MedicationDTO medication) throws Exception;
}
