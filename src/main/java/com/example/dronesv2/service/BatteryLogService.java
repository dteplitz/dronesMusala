package com.example.dronesv2.service;

import com.example.dronesv2.model.BatteryLog;
import com.example.dronesv2.model.Drone;

import java.time.LocalDateTime;
import java.util.List;

public interface BatteryLogService {
    void logBatteryLevel(Drone drone);
    List<BatteryLog> getBatteryLogs(String droneSerialNumber, LocalDateTime from, LocalDateTime to);
}
