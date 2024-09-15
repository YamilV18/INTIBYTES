package com.example.ms_usersubscription.controller;

import com.example.ms_usersubscription.entity.Subscription;
import com.example.ms_usersubscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<Subscription>> list() {
        List<Subscription> subscriptions = subscriptionService.list();
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping
    public ResponseEntity<Subscription> save(@RequestBody Subscription subscription) {
        Subscription savedSubscription = subscriptionService.save(subscription);
        return ResponseEntity.ok(savedSubscription);
    }

    @PutMapping
    public ResponseEntity<Subscription> update(@RequestBody Subscription subscription) {
        Subscription updatedSubscription = subscriptionService.update(subscription);
        return ResponseEntity.ok(updatedSubscription);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> listById(@PathVariable Integer id) {
        Optional<Subscription> subscription = subscriptionService.findById(id);
        return subscription.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        subscriptionService.deleteById(id);
        return ResponseEntity.ok("Eliminación de usuario Correcta");
    }
}