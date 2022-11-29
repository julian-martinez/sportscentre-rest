package com.julianmartinez.sportscentrerest.controller;

import com.julianmartinez.sportscentrerest.model.Resource;
import com.julianmartinez.sportscentrerest.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/resources")
    public ResponseEntity<List<Resource>> getAllResources() {
        return ResponseEntity.ok(this.resourceService.retrieveAllResources());
    }

    @GetMapping("/resources/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable final int id) {
        return ResponseEntity.ok(this.resourceService.retrieveResource(id));
    }

    @PostMapping("/resources")
    public ResponseEntity<Resource> postResource(@RequestBody final Resource resource) {
        return ResponseEntity.ok(this.resourceService.addResource(resource));
    }

    @PutMapping("/resources/{id}")
    public ResponseEntity<Resource> putResource(@PathVariable final int id, @RequestBody final Resource resource) {
        return ResponseEntity.ok(this.resourceService.modifyResource(id, resource));
    }

    @DeleteMapping("/resources/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable final int id) {
        this.resourceService.removeResource(id);
        return ResponseEntity.noContent().build();
    }
}
