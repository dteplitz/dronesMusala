package com.example.dronesv2.service;

import com.example.dronesv2.dto.MedicationDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.repository.DroneRepository;
import com.example.dronesv2.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService{
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Override
    public Medication saveMedication(MedicationDTO medication) throws Exception {
        Optional<Medication> medicationActual = medicationRepository.findByCode(medication.getCode());
        if (medicationActual.isPresent()) {
            throw new Exception("Medication already created");
        }
        Medication medicationSave = new Medication(medication.getName(),medication.getWeight(),medication.getCode(),medication.getImage());
        checkMedicationIsValid(medicationSave);
        return medicationRepository.save(medicationSave);
    }

    private void checkMedicationIsValid(Medication medication) throws Exception {
        if(!checkValidName(medication.getName())){
            throw new Exception("Name only can have letters, numbers, - and _");
        }
        if(!checkValidCode(medication.getCode())){
            throw new Exception("Code can only have upper case letters, underscore and numbers");
        }
    }

    private boolean checkValidCode(String code) {
        String regex = "^[A-Z0-9_]*$";
        return code.matches(regex);
    }

    private boolean checkValidName(String name) {
        String regex = "^[a-zA-Z0-9_-]*$";
        return name.matches(regex);
    }
}
