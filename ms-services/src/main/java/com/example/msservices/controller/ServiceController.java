package com.example.msservices.controller;

import com.example.msservices.entity.Service;
import com.example.msservices.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @GetMapping
    public ResponseEntity<List<Service>> getAll() {
        return ResponseEntity.ok(serviceService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Service>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Service> create(Service service) {
        return ResponseEntity.ok(serviceService.save(service));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Service> update(@PathVariable Integer id, @RequestBody Service service) {
        service.setId(id);
        return ResponseEntity.ok(serviceService.save(service));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Service>> delete(@PathVariable Integer id) {
        serviceService.delete(id);
        return ResponseEntity.ok(serviceService.findAll());
    }
}
