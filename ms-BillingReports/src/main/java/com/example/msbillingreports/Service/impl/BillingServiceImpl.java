package com.example.msbillingreports.Service.impl;

import com.example.msbillingreports.Entity.Billing;

import com.example.msbillingreports.Repository.BillingRepository;

import com.example.msbillingreports.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    private BillingRepository billingRepository;

    @Override
    public List<Billing> list() {
        return billingRepository.findAll();
    }

    @Override
    public Optional<Billing> findById(Integer id) {
        return billingRepository.findById(id);
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
