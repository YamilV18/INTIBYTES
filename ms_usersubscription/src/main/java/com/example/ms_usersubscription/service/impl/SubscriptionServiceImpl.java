package com.example.ms_usersubscription.service.impl;

import com.example.ms_usersubscription.dto.ServiceDto;
import com.example.ms_usersubscription.entity.Subscription;
import com.example.ms_usersubscription.feign.ServiceFeign;
import com.example.ms_usersubscription.repository.SubscriptionRepository;
import com.example.ms_usersubscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ServiceFeign serviceFeign;


    @Override
    public List<Subscription> list() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription update(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Optional<Subscription> findById(Integer id) {
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findById(id);
        if (subscriptionOpt.isPresent()) {
            Subscription subscription = subscriptionOpt.get();

            // Obtener cliente
            ResponseEntity<Optional<ServiceDto>> serviceResponse = serviceFeign.getById(subscription.getServiceId());
            if (serviceResponse.getStatusCode().is2xxSuccessful()) {
                Optional<ServiceDto> servicedto = serviceResponse.getBody();
                if (servicedto.isPresent()) {
                    subscription.setService(servicedto.get());
                    System.out.println("Cliente obtenido: " + servicedto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }



            //subscription.setUser(user);
            return Optional.of(subscription);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) { subscriptionRepository.deleteById(id);
    }

}