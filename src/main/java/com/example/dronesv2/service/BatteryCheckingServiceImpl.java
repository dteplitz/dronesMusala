package com.example.dronesv2.service;

import com.example.dronesv2.model.BatteryLog;
import com.example.dronesv2.model.Drone;
import com.example.dronesv2.repository.JpaBatteryLogRepository;
import com.example.dronesv2.repository.JpaDroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryCheckingServiceImpl implements BatteryCheckingService{
    private final JpaDroneRepository droneRepository;
    private final JpaBatteryLogRepository batteryLogRepository;
    @Autowired
    public BatteryCheckingServiceImpl(JpaDroneRepository droneRepository, JpaBatteryLogRepository batteryLogRepository) {
        this.droneRepository = droneRepository;
        this.batteryLogRepository = batteryLogRepository;
    }

    @Scheduled(fixedDelay = 60000)
    @Override
    public void checkBatteryLevels() {
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            logBatteryLevel(drone);
        }
    }

    @Override
    public List<BatteryLog> getBatteryLogs(String droneSerialNumber, LocalDateTime from, LocalDateTime to) {
        return batteryLogRepository.findBatteryLogsByDroneSerialNumberIs(droneSerialNumber).stream()
                .filter(b -> b.getTimestamp().isAfter(from) && b.getTimestamp().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<BatteryLog> getBatteryLogs(LocalDateTime from, LocalDateTime to) {
        return batteryLogRepository.findAll().stream()
                .filter(b -> b.getTimestamp().isAfter(from) && b.getTimestamp().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBatteryLog(Long batteryLogID) {
        batteryLogRepository.deleteById(batteryLogID);
    }

    private void logBatteryLevel(Drone drone) {
        BatteryLog batteryLog = new BatteryLog(drone.getSerialNumber(),drone.getBatteryCapacity());
        batteryLogRepository.save(batteryLog);
    }
}
