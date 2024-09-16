package com.example.msbillingreports.Service.impl;

import com.example.msbillingreports.Dto.SubscriptionDto;
import com.example.msbillingreports.Entity.Billing;

import com.example.msbillingreports.Feign.SubscriptionFeign;
import com.example.msbillingreports.Feign.SubscriptionFeign;
import com.example.msbillingreports.Repository.BillingRepository;

import com.example.msbillingreports.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    private BillingRepository billingRepository;
    @Autowired
    private SubscriptionFeign subscriptionFeign;

    @Override
    public List<Billing> list() {
        return billingRepository.findAll();
    }

    @Override
    public Optional<Billing> findById(Integer id) {
        Optional<Billing> billingOpt = billingRepository.findById(id);
        if (billingOpt.isPresent()) {
            Billing billing = billingOpt.get();

            // Obtener cliente
            ResponseEntity<Optional<SubscriptionDto>> subscriptionResponse = subscriptionFeign.listById(billing.getSubscriptionId());
            if (subscriptionResponse.getStatusCode().is2xxSuccessful()) {
                Optional<SubscriptionDto> subscriptiondto = subscriptionResponse.getBody();
                if (subscriptiondto.isPresent()) {
                    billing.setSubscription(subscriptiondto.get());
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
        return billingRepository.save(billing);
    }

    @Override
    public Billing update(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public void delete(Integer id) {
        billingRepository.deleteById(id);
    }
}
