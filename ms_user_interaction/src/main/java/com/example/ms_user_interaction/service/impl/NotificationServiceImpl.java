package com.example.ms_user_interaction.service.impl;

import com.example.ms_user_interaction.dto.UserDto;
import com.example.ms_user_interaction.entity.Notification;
import com.example.ms_user_interaction.feign.UserFeign;
import com.example.ms_user_interaction.repository.NotificationRepository;
import com.example.ms_user_interaction.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserFeign userFeign;
        
    @Override
    public List<Notification> list() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Integer id) {
        Optional<Notification> notificationOpt = notificationRepository.findById(id);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();

            // Obtener cliente (sin Optional)
            ResponseEntity<UserDto> userResponse = userFeign.listById(notification.getUserId());
            if (userResponse.getStatusCode().is2xxSuccessful()) {
                UserDto userdto = userResponse.getBody();  // Sin Optional
                if (userdto != null) {
                    notification.setUser(userdto);
                    System.out.println("Cliente obtenido: " + userdto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }

            //notification.setUser(user);
            return Optional.of(notification);
        }
        return Optional.empty();

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
