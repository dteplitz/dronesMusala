package com.example.dronesv2.repository;

import com.example.dronesv2.dto.MedicationDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicationRepository  extends JpaRepository<Medication,Long> {
    Optional<Medication> findByCode(String code);
    List<Medication> findAll();
    Medication save(Medication medication);
    Medication save(MedicationDTO medication);
    void deleteByCode(String code);
}
