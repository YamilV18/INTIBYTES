package com.example.ms_user_interaction.controller;


import com.example.ms_user_interaction.dto.ErrorResponseDto;
import com.example.ms_user_interaction.dto.UserDto;
import com.example.ms_user_interaction.entity.Notification;
import com.example.ms_user_interaction.entity.Review;
import com.example.ms_user_interaction.feign.UserFeign;
import com.example.ms_user_interaction.service.NotificationService;
import com.example.ms_user_interaction.service.ReviewService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private ReviewService reviewService; // Agregamos ReviewService

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
    public ResponseEntity<?> create(@RequestBody Notification notification) {
        try {
            UserDto userDto = userFeign.getById(notification.getUserId()).getBody();

            // Si el usuario no existe, se puede considerar que userDto es nulo
            if (userDto == null || userDto.getId() == null) {
                String errorMessage = "Error: Usuario no encontrado.";
                ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Si el usuario es válido, guardar la notificación
            Notification newNotification = notificationService.save(notification);
            return ResponseEntity.ok(newNotification);

        } catch (FeignException.NotFound e) {
            // Manejo específico del error 404
            String errorMessage = "Error: Usuario no encontrado.";
            ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception e) {
            // Manejo genérico de excepciones
            String errorMessage = "Error al procesar la solicitud, revise la existencia de el usuario o la reseña";
            ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
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
