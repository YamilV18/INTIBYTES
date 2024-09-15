package com.example.ms_usersubscription.service;

import com.example.ms_usersubscription.entity.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    public List<Subscription> list();
    public Subscription save(Subscription subscription);
    public Subscription update(Subscription subscription);
    public Optional<Subscription> findById(Integer id);
    public void deleteById(Integer id);
}