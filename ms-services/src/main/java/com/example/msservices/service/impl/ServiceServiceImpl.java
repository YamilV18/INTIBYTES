package com.example.msservices.service.impl;

import com.example.msservices.repository.ServiceRepository;
import com.example.msservices.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<com.example.msservices.entity.Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Optional<com.example.msservices.entity.Service> findById(Integer id) {
        return serviceRepository.findById(id);
    }

    @Override
    public com.example.msservices.entity.Service save(com.example.msservices.entity.Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public com.example.msservices.entity.Service update(com.example.msservices.entity.Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void delete(Integer id) {
        serviceRepository.deleteById(id);
    }
}
