package com.example.ms_user_interaction.service;

import com.example.ms_user_interaction.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<Notification> list();
    Optional<Notification> findById(Integer id);
    Notification save(Notification notification);
    Notification update(Notification notification);
    void delete(Integer id);
}
