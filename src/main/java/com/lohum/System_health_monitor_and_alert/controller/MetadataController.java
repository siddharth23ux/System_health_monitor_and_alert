package com.lohum.System_health_monitor_and_alert.controller;

import com.lohum.System_health_monitor_and_alert.model.Metadata;
import com.lohum.System_health_monitor_and_alert.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/metadata")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    // ✅ Add new metadata
    @PostMapping
    public ResponseEntity<Metadata> createMetadata(@RequestBody Metadata metadata) {
        Metadata savedMetadata = metadataService.saveMetadata(metadata);
        return ResponseEntity.ok(savedMetadata);
    }

    // ✅ Get all metadata
    @GetMapping
    public ResponseEntity<List<Metadata>> getAllMetadata() {
        return ResponseEntity.ok(metadataService.getAllMetadata());
    }

    // ✅ Update metadata by ID
    @PutMapping("/{id}")
    public ResponseEntity<Metadata> updateMetadata(@PathVariable Long id, @RequestBody Metadata metadataDetails) {
        Optional<Metadata> updatedMetadata = metadataService.updateMetadata(id, metadataDetails);
        return updatedMetadata.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Delete metadata by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMetadata(@PathVariable Long id) {
        boolean deleted = metadataService.deleteMetadata(id);
        return deleted ? ResponseEntity.ok("Metadata deleted successfully.") : ResponseEntity.notFound().build();
    }
}
