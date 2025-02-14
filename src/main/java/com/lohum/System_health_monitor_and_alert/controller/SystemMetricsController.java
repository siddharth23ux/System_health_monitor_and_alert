package com.lohum.System_health_monitor_and_alert.controller;

import com.lohum.System_health_monitor_and_alert.model.SystemMetrics;
import com.lohum.System_health_monitor_and_alert.service.SystemMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/metrics")
public class SystemMetricsController {

    @Autowired
    private SystemMetricsService systemMetricsService;

    //  Get real-time metrics
    @GetMapping
    public ResponseEntity<Map<String, Object>> getSystemMetrics() {
        SystemMetrics metrics = systemMetricsService.getCurrentMetrics();
        Map<String, Object> response = new HashMap<>();
        response.put("cpuUsage", metrics.getCpuUsage());
        response.put("memoryUsage", metrics.getMemoryUsage());
        response.put("diskUsage", metrics.getDiskUsage());
        return ResponseEntity.ok(response);
    }

    //  Store metrics (runs periodically)
    @PostMapping("/save")
    public ResponseEntity<SystemMetrics> saveMetrics() {
        return ResponseEntity.ok(systemMetricsService.saveMetrics());
    }

    //  Get historical metrics
    @GetMapping("/history")
    public ResponseEntity<List<SystemMetrics>> getMetricsHistory() {
        return ResponseEntity.ok(systemMetricsService.getHistoricalMetrics());
    }
}
