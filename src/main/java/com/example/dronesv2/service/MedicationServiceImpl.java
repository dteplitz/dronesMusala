package com.example.dronesv2.service;

import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.repository.DroneRepository;
import com.example.dronesv2.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImpl implements MedicationService{
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Medication saveMedication(Medication medication) {
        return medicationRepository.save(medication);
    }
}
