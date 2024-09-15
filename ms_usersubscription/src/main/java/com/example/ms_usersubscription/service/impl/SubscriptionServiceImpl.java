package com.example.ms_usersubscription.service.impl;

import com.example.ms_usersubscription.entity.Subscription;
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
        return subscriptionRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) { subscriptionRepository.deleteById(id);
    }

}