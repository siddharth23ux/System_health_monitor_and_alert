package com.lohum.System_health_monitor_and_alert.service;

import com.lohum.System_health_monitor_and_alert.model.Alert;
import com.lohum.System_health_monitor_and_alert.model.Threshold;
import com.lohum.System_health_monitor_and_alert.repository.AlertRepository;
import com.lohum.System_health_monitor_and_alert.repository.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AlertService {

    private static final Logger LOGGER = Logger.getLogger(AlertService.class.getName());

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private ThresholdRepository thresholdRepository;

    @Autowired
    private SystemMetricsCollector systemMetricsCollector;

    /**
     * This scheduler runs every 20 seconds and checks if real-time metrics have exceeded defined thresholds.
     * If a threshold is violated, an alert is created, saved in the database, and logged in the console.
     */
    @Scheduled(fixedRate = 20000) // Runs every 20 seconds
    public void checkAndCreateAlerts() {
        List<Threshold> thresholds = thresholdRepository.findAll();

        for (Threshold threshold : thresholds) {
            double actualValue = getMetricValue(threshold.getMetricType());

            boolean isThresholdBreached = (threshold.getComparisonType().equals("GREATER") && actualValue > threshold.getThresholdValue()) ||
                    (threshold.getComparisonType().equals("SMALLER") && actualValue < threshold.getThresholdValue());

            if (isThresholdBreached) {
                Alert alert = new Alert(threshold.getMetricType(), threshold.getThresholdValue(), actualValue, "ACTIVE", LocalDateTime.now(), threshold.getComparisonType());
                alertRepository.save(alert);

                // ✅ Log alert to console
                LOGGER.warning("🚨 ALERT TRIGGERED: " + threshold.getMetricType() +
                        " threshold breached! [Threshold: " + threshold.getThresholdValue() +
                        ", Actual: " + actualValue + "]");
            }
        }
    }

    /**
     * This scheduler runs every 30 seconds and checks if any previously created alerts
     * can be resolved based on real-time system metrics.
     */
    @Scheduled(fixedRate = 30000) // Runs every 30 seconds
    public void checkAndResolveAlerts() {
        List<Alert> activeAlerts = alertRepository.findByStatus("ACTIVE");

        for (Alert alert : activeAlerts) {
            double currentMetricValue = getMetricValue(alert.getMetricType());

            boolean isMetricBackToNormal = (alert.getThresholdComparisonType().equals("GREATER") && currentMetricValue <= alert.getThreshold()) ||
                    (alert.getThresholdComparisonType().equals("SMALLER") && currentMetricValue >= alert.getThreshold());

            if (isMetricBackToNormal) {
                alert.setStatus("RESOLVED");
                alertRepository.save(alert);

                // ✅ Log alert resolution to console
                LOGGER.info("✅ ALERT RESOLVED: " + alert.getMetricType() +
                        " is back within normal limits. [Threshold: " + alert.getThreshold() +
                        ", Current: " + currentMetricValue + "]");
            }
        }
    }

    /**
     * Fetches the real-time metric value from the SystemMetricsService based on the metric type.
     */
    private double getMetricValue(String metricType) {
        return switch (metricType.toUpperCase()) {
            case "CPU" -> systemMetricsCollector.getCpuUsage();
            case "MEMORY" -> systemMetricsCollector.getMemoryUsage();
            case "DISK" -> systemMetricsCollector.getDiskUsage();
            default -> throw new IllegalArgumentException("Invalid metric type: " + metricType);
        };
    }

    public List<Alert> findAllAlerts() {
        return alertRepository.findAll();
    }

    public List<Alert> findByStatus(String status) {
        return alertRepository.findByStatus(status);
    }

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }
}
