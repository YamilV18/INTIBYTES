package com.example.ms_usersubscription.controller;

import com.example.ms_usersubscription.entity.Subscription;
import com.example.ms_usersubscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.ms_usersubscription.dto.ErrorResponseDto;
import com.example.ms_usersubscription.dto.ServiceDto;
import com.example.ms_usersubscription.feign.ServiceFeign;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ServiceFeign serviceFeign;

    @GetMapping
    public ResponseEntity<List<Subscription>> list() {
        List<Subscription> subscriptions = subscriptionService.list();
        return ResponseEntity.ok(subscriptions);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Subscription subscription) {
        try {
            ServiceDto serviceDto = serviceFeign.getById(subscription.getServiceId()).getBody();

            // Si el usuario no existe, se puede considerar que userDto es nulo
            if (serviceDto == null || serviceDto.getId() == null) {
                String errorMessage = "Error: Servicio no encontrado.";
                ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Si el usuario es válido, guardar la notificación
            Subscription newNotification = subscriptionService.save(subscription);
            return ResponseEntity.ok(newNotification);

        } catch (FeignException.NotFound e) {
            // Manejo específico del error 404
            String errorMessage = "Error: Servicio no encontrado.";
            ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception e) {
            // Manejo genérico de excepciones
            String errorMessage = "Error al procesar la solicitud, revise la existencia de el servicio o el usuario";
            ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> update(@PathVariable Integer id, @RequestBody Subscription subscription) {
        subscription.setId(id);
        return ResponseEntity.ok(subscriptionService.update(subscription));
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