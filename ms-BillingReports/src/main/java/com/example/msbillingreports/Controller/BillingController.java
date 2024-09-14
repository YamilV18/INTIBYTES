package com.example.msbillingreports.Controller;

import com.example.msbillingreports.Entity.Billing;
import com.example.msbillingreports.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/billing")
public class BillingController {
    @Autowired
    private BillingService billingService;

    @GetMapping
    public ResponseEntity<List<Billing>> getAll() {
        return ResponseEntity.ok(billingService.list());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Billing>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(billingService.findById(id));
    }
    @PostMapping
    public ResponseEntity<Billing> create(@RequestBody Billing billing) {
        return ResponseEntity.ok(billingService.save(billing));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Billing> update(@PathVariable Integer id,
                                         @RequestBody Billing billing) {
        billing.setId(id);
        return ResponseEntity.ok(billingService.save(billing));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Billing>> delete(@PathVariable Integer id) {
        billingService.delete(id);
        return ResponseEntity.ok(billingService.list());
    }
}
