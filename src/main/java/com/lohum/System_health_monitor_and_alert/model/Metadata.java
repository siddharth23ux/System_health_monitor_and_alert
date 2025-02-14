package com.lohum.System_health_monitor_and_alert.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "metadata")
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "environment")
    private String environment;
    @Column(name = "location")
    private String location;

    public Metadata() {}

    public Metadata(String name, String environment, String location) {
        this.name = name;
        this.environment = environment;
        this.location = location;
    }
}
