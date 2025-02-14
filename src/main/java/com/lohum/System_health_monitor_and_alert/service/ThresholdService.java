package com.lohum.System_health_monitor_and_alert.service;

import com.lohum.System_health_monitor_and_alert.model.Threshold;
import com.lohum.System_health_monitor_and_alert.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThresholdService {

    @Autowired
    private ThresholdRepository thresholdRepository;

    // Save metadata to the database
    public Threshold saveThreshold(Threshold threshold) {
        return thresholdRepository.save(threshold);
    }

    public List<Threshold> findAllThreshold() {
        return thresholdRepository.findAll();
    }
}
