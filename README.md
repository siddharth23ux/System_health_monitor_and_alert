Deployment URL: https://system-health-monitor-and-alert-prod.onrender.com

System Health Monitor and Alert API

Using Springboot, postgres, pgAdmin, render, insomnia

Overview

This API provides endpoints for monitoring system metrics, managing alerts, and configuring thresholds. It enables users to track CPU, memory, and disk usage, set thresholds for alerts, and retrieve real-time and historical system data.

Base URL

http://DeploymentURL/api

Endpoints

1. Alerts API (/api/alerts)

🔹 Get All Alerts (Active & Resolved)

Request:

GET /api/alerts

Response:

  
  
    [{"id": 1,
    
    "metricType": "CPU",
    
    "threshold": 80.0,
    
    "actualValue": 85.5,
    
    "status": "ACTIVE",
    
    "timestamp": "2025-02-14T10:15:30"}]
  

🔹 Get Active Alerts

Request:

GET /api/alerts/active

🔹 Get Resolved Alerts

Request:

GET /api/alerts/resolved

🔹 Create a New Alert

Request:

POST /api/alerts

Content-Type: application/json

Body:

{
  "metricType": "MEMORY",
  "threshold": 70.0,
  "actualValue": 75.3,
  "status": "ACTIVE"
}

Response:

{
  "id": 2,
  "metricType": "MEMORY",
  "threshold": 70.0,
  "actualValue": 75.3,
  "status": "ACTIVE",
  "timestamp": "2025-02-14T10:20:45"
}

2. Metadata API (/api/metadata)

🔹 Create Metadata

Request:

POST /api/metadata

Content-Type: application/json

Body:

{
  "name": "Server A",
  "environment": "Production",
  "location": "New York"
}

🔹 Get All Metadata

Request:

GET /api/metadata

🔹 Update Metadata by ID

Request:

PUT /api/metadata/{id}

Content-Type: application/json

Body:

{
  "name": "Updated Server A",
  "environment": "Staging",
  "location": "California"
}

🔹 Delete Metadata by ID

Request:

DELETE /api/metadata/{id}

3. System Metrics API (/api/metrics)

🔹 Get Real-Time System Metrics

Request:

GET /api/metrics

Response:

{
  "cpuUsage": 55.5,
  "memoryUsage": 72.3,
  "diskUsage": 40.2
}

🔹 Store System Metrics (Triggered Periodically)

Request:

POST /api/metrics/save

🔹 Get Historical Metrics

Request:

GET /api/metrics/history

4. Threshold API (/api/thresholds)

🔹 Create a New Threshold

Request:

POST /api/thresholds

Content-Type: application/json

Body:

{
  "metricType": "CPU",
  "thresholdValue": 85.0,
  "comparisonType": "GREATER"
}

🔹 Get All Thresholds

Request:

GET /api/thresholds

Response Codes

200 OK - The request was successful.

201 Created - A new resource was successfully created.

400 Bad Request - The request was invalid or malformed.

404 Not Found - The requested resource was not found.

500 Internal Server Error - An error occurred on the server.

Running the API Locally

./mvnw spring-boot:run

Deployment

To deploy this API on Render or another cloud provider, ensure Java 17+ is used and update the Dockerfile accordingly.


WORKED ON SPRINGBOOT DURING WORK EXPERIENCE, USED CHATGPT TO GET THE IMPLEMENTATION HELP.


There are two schedulers running in AlertService one for checking the live metric detection and condition checking. Other is for resolving the status of ACTIVE alerts stored in database. 

SystemMetricsCollector is for getting the live system statistics.

SystemMetricsService is for directing information to SystemMetricsCollector and every 10 one minute storing the live metrics in systemmetrics table.

Metrics are logged into the table at every 1 minute and metrics more than 6 hours ago are deleted. The alerts if any are created every 20 seconds and are set to resolved every 30 seconds.

there are 4 tables:

    1. 
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
    }


    2. 
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
    }


    3. 
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
    }


    4. 
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
    } 
    
