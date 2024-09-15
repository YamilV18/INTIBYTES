package com.example.ms_usersubscription.service.impl;

import com.example.ms_usersubscription.dto.ServiceDTO;
import com.example.ms_usersubscription.entity.Subscription;
import com.example.ms_usersubscription.entity.User;
import com.example.ms_usersubscription.feign.ServiceFeign;
import com.example.ms_usersubscription.repository.SubscriptionRepository;
import com.example.ms_usersubscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteById(Integer id) {
        subscriptionRepository.deleteById(id);
    }

    // New method to find subscription by ID and enrich user with service details
    @Override
    public Optional<Subscription> findById(Integer id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            // For each user, fetch the service details using Feign
            for (User user : subscription.get().getUsers()) {
                // Fetch service details from ms-services using Feign
                ServiceDTO serviceDTO = serviceFeign.getById(user.getService().getId()).getBody();
                // Set the service details in the User entity
                user.setService(serviceDTO);
            }
        }
        return subscription;
    }
}