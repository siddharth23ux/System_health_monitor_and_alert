package com.lohum.System_health_monitor_and_alert.repository;

import com.lohum.System_health_monitor_and_alert.model.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThresholdRepository extends JpaRepository<Threshold, Long> {
    List<Threshold> findByMetricType(String metricType);
}
