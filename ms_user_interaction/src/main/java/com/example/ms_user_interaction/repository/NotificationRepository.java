package com.example.ms_user_interaction.repository;

import com.example.ms_user_interaction.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByMessage(String message);
}
