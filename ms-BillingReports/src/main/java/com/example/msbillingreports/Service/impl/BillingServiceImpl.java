package com.example.msbillingreports.Service.impl;

import com.example.msbillingreports.Dto.SubscriptionDto;
import com.example.msbillingreports.Entity.Billing;

import com.example.msbillingreports.Feign.UserSubscriptionFeign;
import com.example.msbillingreports.Repository.BillingRepository;

import com.example.msbillingreports.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private UserSubscriptionFeign userSubFeign;

    @Override
    public List<Billing> list() {
        List<Billing> billings = billingRepository.findAll();

        // Para cada orden en la lista, obtenemos los detalles del cliente y productos
        return billings.stream().map(billing -> {
            // Obtener cliente
            ResponseEntity<SubscriptionDto> subscriptionResponse = userSubFeign.listSubById(billing.getSubscriptionId());
            if (subscriptionResponse.getStatusCode().is2xxSuccessful()) {
                SubscriptionDto subscriptiondto = subscriptionResponse.getBody();  // Sin Optional
                if (subscriptiondto != null) {
                    billing.setSubscription(subscriptiondto);
                    System.out.println("Cliente obtenido: " + subscriptiondto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }

            return billing;
        }).collect(Collectors.toList());
    }

    @Override
    public Optional<Billing> findById(Integer id) {
        Optional<Billing> billingOpt = billingRepository.findById(id);
        if (billingOpt.isPresent()) {
            Billing billing = billingOpt.get();

            // Obtener cliente (sin Optional)
            ResponseEntity<SubscriptionDto> subscriptionResponse = userSubFeign.listSubById(billing.getSubscriptionId());
            if (subscriptionResponse.getStatusCode().is2xxSuccessful()) {
                SubscriptionDto subscriptiondto = subscriptionResponse.getBody();  // Sin Optional
                if (subscriptiondto != null) {
                    billing.setSubscription(subscriptiondto);
                    System.out.println("Cliente obtenido: " + subscriptiondto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }

            //billing.setSubscription(subscription);
            return Optional.of(billing);
        }
        return Optional.empty();

    }

    @Override
    public Billing save(Billing billing) {
        // Guardar el objeto Billing en el repositorio
        Billing savedBilling = billingRepository.save(billing);

        // Obtener cliente (sin Optional) si hay un subscriptionId
        if (savedBilling.getSubscriptionId() != null) {
            ResponseEntity<SubscriptionDto> subscriptionResponse = userSubFeign.listSubById(savedBilling.getSubscriptionId());
            if (subscriptionResponse.getStatusCode().is2xxSuccessful()) {
                SubscriptionDto subscriptionDto = subscriptionResponse.getBody();  // Sin Optional
                if (subscriptionDto != null) {
                    savedBilling.setSubscription(subscriptionDto);
                    System.out.println("Cliente obtenido: " + subscriptionDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedBilling;
    }

    @Override
    public Billing update(Billing billing) {
        // Guardar cambios de Billing en el repositorio
        Billing savedBilling = billingRepository.save(billing);

        // Obtener cliente (sin Optional) si hay un subscriptionId
        if (savedBilling.getSubscriptionId() != null) {
            ResponseEntity<SubscriptionDto> subscriptionResponse = userSubFeign.listSubById(savedBilling.getSubscriptionId());
            if (subscriptionResponse.getStatusCode().is2xxSuccessful()) {
                SubscriptionDto subscriptionDto = subscriptionResponse.getBody();  // Sin Optional
                if (subscriptionDto != null) {
                    savedBilling.setSubscription(subscriptionDto);
                    System.out.println("Cliente obtenido: " + subscriptionDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedBilling;    }

    @Override
    public void delete(Integer id) {
        billingRepository.deleteById(id);
    }
}
