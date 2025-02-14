package com.lohum.System_health_monitor_and_alert.service;

import com.lohum.System_health_monitor_and_alert.model.SystemMetrics;
import com.lohum.System_health_monitor_and_alert.repository.SystemMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SystemMetricsService {

    @Autowired
    private SystemMetricsRepository systemMetricsRepository;

    @Autowired
    private SystemMetricsCollector systemMetricsCollector;

    public SystemMetrics getCurrentMetrics() {
        return new SystemMetrics(
                systemMetricsCollector.getCpuUsage(),
                systemMetricsCollector.getMemoryUsage(),
                systemMetricsCollector.getDiskUsage()
        );
    }

    public SystemMetrics saveMetrics() {
        SystemMetrics metrics = getCurrentMetrics();
        return systemMetricsRepository.save(metrics);
    }

    public List<SystemMetrics> getHistoricalMetrics() {
        return systemMetricsRepository.findByOrderByTimestampDesc();
    }

    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void saveMetricsPeriodically() {
        saveMetrics();
    }

    @Scheduled(fixedRate = 3600000) // Runs every hour
    public void deleteOldMetrics() {
        Instant sixHoursAgo = Instant.now().minusSeconds(6 * 3600);
        systemMetricsRepository.deleteByTimestampBefore(sixHoursAgo);
    }
}
