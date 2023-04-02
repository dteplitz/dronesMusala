package com.example.dronesv2.repository;

import com.example.dronesv2.model.BatteryLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BatteryLogRepository extends JpaRepository<BatteryLog,Long> {
    Optional<BatteryLog> findById(Long id);
    List<BatteryLog> findAll();
    BatteryLog save(BatteryLog batteryLog);
    List<BatteryLog> findBatteryLogsByDroneSerialNumberIs(String droneSerialNumber);
}

