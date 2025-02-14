package com.lohum.System_health_monitor_and_alert.controller;

import com.lohum.System_health_monitor_and_alert.model.Alert;
import com.lohum.System_health_monitor_and_alert.repository.AlertRepository;
import com.lohum.System_health_monitor_and_alert.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class AlertsController {

    @Autowired
    private AlertService alertService;

    // Fetch all alerts (active and resolved)
    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.findAllAlerts());
    }

    // Fetch only active alerts
    @GetMapping("/active")
    public ResponseEntity<List<Alert>> getActiveAlerts() {
        return ResponseEntity.ok(alertService.findByStatus("ACTIVE"));
    }

    // Fetch only resolved alerts
    @GetMapping("/resolved")
    public ResponseEntity<List<Alert>> getResolvedAlerts() {
        return ResponseEntity.ok(alertService.findByStatus("RESOLVED"));
    }

    // Add a new alert
    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        alert.setTimestamp(LocalDateTime.now());
        return ResponseEntity.ok(alertService.saveAlert(alert));
    }
}
