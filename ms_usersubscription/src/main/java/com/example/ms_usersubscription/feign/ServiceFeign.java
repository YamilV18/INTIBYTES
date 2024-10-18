package com.example.ms_usersubscription.feign;

import com.example.ms_usersubscription.dto.ServiceDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(name="ms-services-service", path="/service")
public interface ServiceFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "servicioListarPorIdCB", fallbackMethod = "servicioListarPorId")

    public ResponseEntity<ServiceDto> getById(@PathVariable Integer id);
    default ResponseEntity<ServiceDto> servicioListarPorId(Integer id, Exception e) {
        return ResponseEntity.ok(new ServiceDto());
    }
}