package com.example.ms_user_interaction.controller;


import com.example.ms_user_interaction.entity.Notification;
import com.example.ms_user_interaction.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAll() {
        return ResponseEntity.ok(notificationService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getById(@PathVariable Integer id) {
        Optional<Notification> notificationOpt = notificationService.findById(id);
        if (notificationOpt.isPresent()) {
            return ResponseEntity.ok(notificationOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<Notification> create(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.save(notification));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> update(@PathVariable Integer id,
                                          @RequestBody Notification notification) {
        notification.setId(id);
        return ResponseEntity.ok(notificationService.save(notification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Notification>> delete(@PathVariable Integer id) {
        notificationService.delete(id);
        return ResponseEntity.ok(notificationService.list());
    }
}
