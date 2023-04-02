package com.example.dronesv2.service;

import com.example.dronesv2.model.BatteryLog;

import java.time.LocalDateTime;
import java.util.List;

public interface BatteryCheckingService {
    void checkBatteryLevels();
    List<BatteryLog> getBatteryLogs(String droneSerialNumber, LocalDateTime from, LocalDateTime to);
    List<BatteryLog> getBatteryLogs(LocalDateTime from, LocalDateTime to);
}
