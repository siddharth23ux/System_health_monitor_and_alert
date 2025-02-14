package com.lohum.System_health_monitor_and_alert.controller;

import com.lohum.System_health_monitor_and_alert.model.Threshold;
import com.lohum.System_health_monitor_and_alert.service.ThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/thresholds")
public class ThresholdController {

    @Autowired
    private ThresholdService thresholdService;

    @PostMapping
    public ResponseEntity<Threshold> createThreshold(@RequestBody Threshold threshold) {
        return ResponseEntity.ok(thresholdService.saveThreshold(threshold));
    }

    @GetMapping
    public ResponseEntity<List<Threshold>> getAllThresholds() {
        return ResponseEntity.ok(thresholdService.findAllThreshold());
    }
}
