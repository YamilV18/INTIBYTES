package com.example.msbillingreports.Repository;

import com.example.msbillingreports.Entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Integer> {
}
