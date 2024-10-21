package com.example.msservices.controller;

import com.example.msservices.entity.Service;
import com.example.msservices.service.CategoryService;
import com.example.msservices.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Service>> getAll() {
        return ResponseEntity.ok(serviceService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Service>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Service service) {
        // Verificar si la categoría existe antes de guardar el servicio
        if (service.getCategory() == null || !categoryService.existsById(service.getCategory().getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: La categoría especificada no existe.");
        }

        // Si la categoría existe, se guarda el servicio
        Service newService = serviceService.save(service);
        return ResponseEntity.ok(newService);
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