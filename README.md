Deployment URL:
https://railway.com/project/f87a3405-5014-4a1b-9a9a-fe5b3a95e1b4/service/a4e82a94-7b11-4631-a717-5e2e8c0baa58?context=2025-02-14T16%3A01%3A01.217665144Z&environmentId=&id=f6346d10-cb0b-4a90-9224-c69fae0259c5&forceViewAll=true#details

System Health Monitor and Alert API

Overview

This API provides endpoints for monitoring system metrics, managing alerts, and configuring thresholds. It enables users to track CPU, memory, and disk usage, set thresholds for alerts, and retrieve real-time and historical system data.

Base URL

http://<your-deployment-url>/api

Endpoints

1. Alerts API (/api/alerts)

🔹 Get All Alerts (Active & Resolved)

Request:

GET /api/alerts

Response:

[
  {
    "id": 1,
    "metricType": "CPU",
    "threshold": 80.0,
    "actualValue": 85.5,
    "status": "ACTIVE",
    "timestamp": "2025-02-14T10:15:30"
  }
]

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
