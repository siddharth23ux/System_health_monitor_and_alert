package com.lohum.System_health_monitor_and_alert.service;

import com.lohum.System_health_monitor_and_alert.model.Metadata;
import com.lohum.System_health_monitor_and_alert.repository.MetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetadataService {

    @Autowired
    private MetadataRepository metadataRepository;

    // ✅ Save new metadata
    public Metadata saveMetadata(Metadata metadata) {
        return metadataRepository.save(metadata);
    }

    // ✅ Get all metadata records
    public List<Metadata> getAllMetadata() {
        return metadataRepository.findAll();
    }

    // ✅ Update existing metadata
    public Optional<Metadata> updateMetadata(Long id, Metadata metadataDetails) {
        return metadataRepository.findById(id).map(metadata -> {
            metadata.setName(metadataDetails.getName());
            metadata.setEnvironment(metadataDetails.getEnvironment());
            metadata.setLocation(metadataDetails.getLocation());
            return metadataRepository.save(metadata);
        });
    }

    // ✅ Delete metadata by ID
    public boolean deleteMetadata(Long id) {
        if (metadataRepository.existsById(id)) {
            metadataRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
