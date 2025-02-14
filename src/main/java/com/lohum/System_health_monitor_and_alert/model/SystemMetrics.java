package com.lohum.System_health_monitor_and_alert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "system_metrics")
public class SystemMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpuusage")
    private double cpuUsage;
    @Column(name = "memoryusage")
    private double memoryUsage;
    @Column(name = "diskusage")
    private double diskUsage;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public SystemMetrics() {
        this.timestamp = LocalDateTime.now();
    }

    public SystemMetrics(double cpuUsage, double memoryUsage, double diskUsage) {
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.diskUsage = diskUsage;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
}
