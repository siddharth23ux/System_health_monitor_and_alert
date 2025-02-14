package com.lohum.System_health_monitor_and_alert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "thresholds")
public class Threshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "metrictype")
    private String metricType; // e.g., CPU, MEMORY, DISK
    @Column(name = "thresholdvalue")
    private Double thresholdValue;
    @Column(name = "comparisontype")
    private String comparisonType; // GREATER or SMALLER

    public Threshold() {}

    public Threshold(String metricType, Double thresholdValue, String comparisonType) {
        this.metricType = metricType;
        this.thresholdValue = thresholdValue;
        this.comparisonType = comparisonType;
    }

}
