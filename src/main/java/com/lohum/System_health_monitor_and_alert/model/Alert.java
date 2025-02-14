package com.lohum.System_health_monitor_and_alert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "metrictype")
    private String metricType; // CPU, MEMORY, DISK
    @Column(name = "threshold")
    private Double threshold;  // e.g., 80% for CPU
    @Column(name = "actualvalue")
    private Double actualValue;
    @Column(name = "status")
    private String status; // "ACTIVE" or "RESOLVED"
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "thresholdcomparisontype")
    private String thresholdComparisonType;

    public Alert() {}

    public Alert(String metricType, Double threshold, Double actualValue, String status, LocalDateTime timestamp, String thresholdComparisonType) {
        this.metricType = metricType;
        this.threshold = threshold;
        this.actualValue = actualValue;
        this.status = status;
        this.timestamp = timestamp;
        this.thresholdComparisonType = thresholdComparisonType;
    }
}

