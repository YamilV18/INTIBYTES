package com.example.ms_usersubscription.service.impl;

import com.example.ms_usersubscription.dto.ServiceDto;
import com.example.ms_usersubscription.entity.Subscription;
import com.example.ms_usersubscription.feign.ServiceFeign;
import com.example.ms_usersubscription.repository.SubscriptionRepository;
import com.example.ms_usersubscription.service.SubscriptionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ServiceFeign serviceFeign;


    @Override
    public List<Subscription> list() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();

        // Para cada orden en la lista, obtenemos los detalles del cliente y productos
        return subscriptions.stream().map(subscription -> {
            // Obtener cliente
            ResponseEntity<ServiceDto> serviceResponse = serviceFeign.getById(subscription.getServiceId());
            if (serviceResponse.getStatusCode().is2xxSuccessful()) {
                ServiceDto servicedto = serviceResponse.getBody();  // Sin Optional
                if (servicedto != null) {
                    subscription.setService(servicedto);
                    System.out.println("Cliente obtenido: " + servicedto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }

            return subscription;
        }).collect(Collectors.toList());
    }

    @Override
    public Subscription save(Subscription subscription) {
        // Guardar el objeto Subscription en el repositorio
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        // Obtener cliente (sin Optional) si hay un serviceId
        if (savedSubscription.getServiceId() != null) {
            ResponseEntity<ServiceDto> serviceResponse = serviceFeign.getById(savedSubscription.getServiceId());
            if (serviceResponse.getStatusCode().is2xxSuccessful()) {
                ServiceDto serviceDto = serviceResponse.getBody();  // Sin Optional
                if (serviceDto != null) {
                    savedSubscription.setService(serviceDto);
                    System.out.println("Cliente obtenido: " + serviceDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedSubscription;
    }

    @Override
    public Subscription update(Subscription subscription) {
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        if (savedSubscription.getServiceId() != null) {
            ResponseEntity<ServiceDto> serviceResponse = serviceFeign.getById(savedSubscription.getServiceId());
            if (serviceResponse.getStatusCode().is2xxSuccessful()) {
                ServiceDto serviceDto = serviceResponse.getBody();  // Sin Optional
                if (serviceDto != null) {
                    savedSubscription.setService(serviceDto);
                    System.out.println("Cliente obtenido: " + serviceDto); // Log para verificar cliente
                } else {
                    System.out.println("Cliente es null"); // Log para verificar si es null
                }
            }
        }

        return savedSubscription;
    }

    @Override
    @CircuitBreaker(name = "servicioListarPorIdCB", fallbackMethod = "servicioListarPorId")
    public Optional<Subscription> findById(Integer id) {
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findById(id);
        if (subscriptionOpt.isPresent()) {
            Subscription subscription = subscriptionOpt.get();

            // Obtener cliente (sin Optional)
            ResponseEntity<ServiceDto> serviceResponse = serviceFeign.getById(subscription.getServiceId());
            if (serviceResponse.getStatusCode().is2xxSuccessful()) {
                ServiceDto servicedto = serviceResponse.getBody();  // Sin Optional
                if (servicedto != null) {
                    subscription.setService(servicedto);
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