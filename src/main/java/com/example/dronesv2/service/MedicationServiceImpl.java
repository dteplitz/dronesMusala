package com.example.dronesv2.service;

import com.example.dronesv2.dto.MedicationDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.Medication;
import com.example.dronesv2.repository.JpaDroneRepository;
import com.example.dronesv2.repository.JpaMedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService{
    private final JpaMedicationRepository medicationRepository;
    private final JpaDroneRepository droneRepository;

    @Autowired
    public MedicationServiceImpl(JpaMedicationRepository medicationRepository, JpaDroneRepository droneRepository) {
        this.medicationRepository = medicationRepository;
        this.droneRepository = droneRepository;
    }

    @Transactional
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

    @Override
    public Medication getMedication(String medicationCode) throws Exception {
        Optional<Medication> medication = medicationRepository.findByCode(medicationCode);
        if(medication.isPresent()){
            return medication.get();
        }
        throw new Exception("Couldn't find medication");
    }

    @Transactional
    @Override
    public Medication updateMedication(MedicationDTO medicationDTO) throws Exception {
        Optional<Medication> medicationActual = medicationRepository.findByCode(medicationDTO.getCode());
        if (!medicationActual.isPresent()) {
            throw new Exception("Medication not found");
        }
        Medication medication = medicationActual.get();
        medication.setImage(medicationDTO.getImage());
        medication.setWeight(medicationDTO.getWeight());
        medication.setName(medicationDTO.getName());

        checkMedicationIsValid(medication);
        return medicationRepository.update(medication);
    }

    @Transactional
    @Override
    public void deleteMedication(String medicationCode) throws Exception {
        List<Drone> drones = droneRepository.findAll();
        Optional<Medication> medicationOptional = medicationRepository.findByCode(medicationCode);
        if(!medicationOptional.isPresent()){
            throw new Exception("Medication not found");
        }
        Medication medication = medicationOptional.get();
        for(Drone drone: drones){
            List<Medication> medications = drone.getMedications();
            if(medications.contains(medication)){
                throw new Exception("Medication used in "+drone.getSerialNumber()+". To delete first remove it from the drone.");
            }
        }
        medicationRepository.deleteByCode(medicationCode);
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
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
