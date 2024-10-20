package com.example.msbillingreports.Controller;

import com.example.msbillingreports.Dto.ErrorResponseDto;
import com.example.msbillingreports.Dto.SubscriptionDto;
import com.example.msbillingreports.Entity.Billing;
import com.example.msbillingreports.Feign.UserSubscriptionFeign;
import com.example.msbillingreports.Repository.BillingRepository;
import com.example.msbillingreports.Service.BillingService;
import com.example.msbillingreports.Service.ReportService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/billing")
public class BillingController {
    @Autowired
    private BillingService billingService;

    @Autowired
    private UserSubscriptionFeign userSubscriptionFeign;

    @Autowired
    private ReportService reportService;

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
        try {
            SubscriptionDto subscriptionDto = userSubscriptionFeign.getById(billing.getSubscriptionId()).getBody();

            // Si el usuario no existe, se puede considerar que es nuloF
            if (subscriptionDto == null || subscriptionDto.getId() == null) {
                String errorMessage = "Error: Usuario no encontrado.";
                ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Si el usuario es válido, guardar la notificación
            Billing newNotification = billingService.save(billing);
            return ResponseEntity.ok(newNotification);

        } catch (FeignException.NotFound e) {
            // Manejo específico del error 404
            String errorMessage = "Error: Suscripcion no encontrado.";
            ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception e) {
            // Manejo genérico de excepciones
            String errorMessage = "Error al procesar la solicitud, revise la existencia de la suscripcion";
            ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
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
