package com.example.dronesv2.controller;

import com.example.dronesv2.model.BatteryLog;
import com.example.dronesv2.service.BatteryCheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/batteryLogs")
public class BatteryLogController {
    private final BatteryCheckingService batteryCheckingService;

    @Autowired
    public BatteryLogController(BatteryCheckingService batteryCheckingService) {
        this.batteryCheckingService = batteryCheckingService;
    }

    @GetMapping("{dateFrom}/{dateTo}/all")
    public ResponseEntity<?> registerMedication(@PathVariable LocalDateTime dateFrom, @PathVariable LocalDateTime dateTo) {
        try{
            List<BatteryLog> batteryLogs = batteryCheckingService.getBatteryLogs(dateFrom,dateTo);
            return ResponseEntity.ok(batteryLogs);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("{dateFrom}/{dateTo}/{droneSerialNumber}")
    public ResponseEntity<?> registerMedication(@PathVariable LocalDateTime dateFrom, @PathVariable LocalDateTime dateTo, @PathVariable String droneSerialNumber) {
        try{
            List<BatteryLog> batteryLogs = batteryCheckingService.getBatteryLogs(droneSerialNumber,dateFrom,dateTo);
            return ResponseEntity.ok(batteryLogs);
        }
        catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
