package com.example.dronesv2.repository;

import com.example.dronesv2.dto.DroneDTO;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.model.DroneState;
import com.example.dronesv2.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone,Long> {
    Optional<Drone> findById(Long id);
    List<Drone> findAll();
    void deleteById(Long id);
    void deleteBySerialNumber(String serialNumber);
    Drone save(Drone drone);
    Optional<Drone> findBySerialNumber(String droneSerialNumber);
    List<Drone> findByState(DroneState state);
}
