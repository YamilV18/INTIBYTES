package com.example.ms_user_interaction.service.impl;

import com.example.ms_user_interaction.dto.UserDto;
import com.example.ms_user_interaction.entity.Notification;
import com.example.ms_user_interaction.feign.UserFeign;
import com.example.ms_user_interaction.repository.NotificationRepository;
import com.example.ms_user_interaction.service.NotificationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserFeign userFeign;
        
    @Override
    public List<Notification> list() {
        List<Notification> notifications = notificationRepository.findAll();

        // Para cada orden en la lista, obtenemos los detalles del cliente y productos
        return notifications.stream().map(notification -> {
            // Obtener cliente
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

            return notification;
        }).collect(Collectors.toList());
    }

    @Override
    @CircuitBreaker(name = "userService", fallbackMethod = "userFallback")
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
        // Guardar el objeto Notification en el repositorio
        Notification savedNotification = notificationRepository.save(notification);

        // Obtener cliente (sin Optional) si hay un userId
        if (savedNotification.getUserId() != null) {
            ResponseEntity<UserDto> userResponse = userFeign.listById(savedNotification.getUserId());
            if (userResponse.getStatusCode().is2xxSuccessful()) {
                UserDto userDto = userResponse.getBody();  // Sin Optional
                if (userDto != null) {
                    savedNotification.setUser(userDto);
                    System.out.println("Cliente obtenido: " + userDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedNotification;
    }

    @Override
    public Notification update(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);

        // Obtener cliente (sin Optional) si hay un userId
        if (savedNotification.getUserId() != null) {
            ResponseEntity<UserDto> userResponse = userFeign.listById(savedNotification.getUserId());
            if (userResponse.getStatusCode().is2xxSuccessful()) {
                UserDto userDto = userResponse.getBody();  // Sin Optional
                if (userDto != null) {
                    savedNotification.setUser(userDto);
                    System.out.println("Cliente obtenido: " + userDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedNotification;
    }

    @Override
    public void delete(Integer id) {
        notificationRepository.deleteById(id);
    }
}
