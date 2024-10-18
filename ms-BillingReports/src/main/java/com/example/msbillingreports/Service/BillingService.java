package com.example.msbillingreports.Service;

import com.example.msbillingreports.Entity.Billing;
import java.util.List;
import java.util.Optional;

public interface BillingService {
    List<Billing> list();
    Optional<Billing> findById(Integer id);
    Billing save(Billing billing);
    Billing update(Billing billing);
    void delete(Integer id);
}
