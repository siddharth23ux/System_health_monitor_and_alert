package com.lohum.System_health_monitor_and_alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SystemHealthMonitorApplication {
	public static void main(String[] args) {
		SpringApplication.run(SystemHealthMonitorApplication.class, args);
	}
}
