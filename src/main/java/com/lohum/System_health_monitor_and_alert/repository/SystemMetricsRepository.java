package com.lohum.System_health_monitor_and_alert.repository;

import com.lohum.System_health_monitor_and_alert.model.SystemMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface SystemMetricsRepository extends JpaRepository<SystemMetrics, Long> {
    List<SystemMetrics> findByOrderByTimestampDesc(); // Get history in descending order

    void deleteByTimestampBefore(Instant sixHoursAgo);
}
