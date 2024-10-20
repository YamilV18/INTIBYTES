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
    public ResponseEntity<Object> create(@RequestBody Billing billing) {
        try {
            // Logging para verificar el ID que se está enviando
            System.out.println("Verificando suscripción con ID: " + billing.getSubscriptionId());

            // Llamada al servicio Feign
            ResponseEntity<SubscriptionDto> response = userSubscriptionFeign.getById(billing.getSubscriptionId());
            SubscriptionDto subscriptionDto = response.getBody();

            // Más logging para verificar la respuesta
            System.out.println("Respuesta del servicio de suscripciones: " + subscriptionDto);

            // Si la suscripción no existe o es nula
            if (subscriptionDto == null || subscriptionDto.getId() == null) {
                String errorMessage = "Error: Suscripción no encontrada.";
                ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            }

            // Si la suscripción es válida, se guarda la facturación
            Billing newBilling = billingService.save(billing);
            return ResponseEntity.ok(newBilling);

        } catch (FeignException.NotFound e) {
            // Manejo del error 404 cuando no se encuentra la suscripción en el servicio remoto
            String errorMessage = "Error: No se encontró la suscripción con el ID proporcionado.";
            ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (Exception e) {
            // Manejo genérico de excepciones
            String errorMessage = "Error al procesar la solicitud, revise la existencia de la suscripción.";
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
