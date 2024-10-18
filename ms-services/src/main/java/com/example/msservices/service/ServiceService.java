package com.example.msservices.service;

import com.example.msservices.entity.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceService {
    public List<Service> findAll();
    public Optional<Service> findById(Integer id);
    public Service save(Service service);
    public Service update(Service service);
    public void delete(Integer id);
}
