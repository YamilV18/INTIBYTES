package com.example.ms_usersubscription.repository;

import com.example.ms_usersubscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}
