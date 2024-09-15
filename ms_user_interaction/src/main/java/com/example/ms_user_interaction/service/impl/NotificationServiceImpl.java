package com.example.ms_user_interaction.service.impl;

import com.example.ms_user_interaction.entity.Notification;
import com.example.ms_user_interaction.repository.NotificationRepository;
import com.example.ms_user_interaction.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> list() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Integer id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void delete(Integer id) {
        notificationRepository.deleteById(id);
    }
}
