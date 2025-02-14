package com.lohum.System_health_monitor_and_alert.repository;

import com.lohum.System_health_monitor_and_alert.model.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<Metadata, Long> {
}
