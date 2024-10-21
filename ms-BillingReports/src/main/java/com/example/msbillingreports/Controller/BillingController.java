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
            SubscriptionDto subscriptionDto = userSubscriptionFeign.listSubById(billing.getSubscriptionId()).getBody();

            if (subscriptionDto == null || subscriptionDto.getId() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDto("Error: Suscripción no encontrada."));
            }

            Billing newBilling = billingService.save(billing);
            return ResponseEntity.ok(newBilling);

        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDto("Error: No se encontró la suscripción con el ID proporcionado."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDto("Error al procesar la solicitud, revise la existencia de la suscripción."));
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