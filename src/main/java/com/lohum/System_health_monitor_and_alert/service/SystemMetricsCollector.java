package com.lohum.System_health_monitor_and_alert.service;

import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.File;

@Service
public class SystemMetricsCollector {

    private final OperatingSystemMXBean osBean =
            (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    // Get CPU Usage
    public double getCpuUsage() {
        return osBean.getCpuLoad() * 100;
    }

    // Get Memory Usage
    public double getMemoryUsage() {
        long totalMemory = osBean.getTotalMemorySize();
        long freeMemory = osBean.getFreeMemorySize();
        return ((double) (totalMemory - freeMemory) / totalMemory) * 100;
    }

    // Get Disk Usage
    public double getDiskUsage() {
        File disk = new File("/");
        long totalDisk = disk.getTotalSpace();
        long freeDisk = disk.getFreeSpace();
        return ((double) (totalDisk - freeDisk) / totalDisk) * 100;
    }

    // Check if any threshold is breached
    public boolean isThresholdBreached() {
        return getCpuUsage() > 80 || getDiskUsage() < 10;
    }
}
