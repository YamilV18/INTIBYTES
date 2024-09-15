package com.example.ms_usersubscription.feign;

import com.example.ms_usersubscription.dto.ServiceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-services")
public interface ServiceFeign {
    @GetMapping("/services/{id}")
    ResponseEntity<ServiceDTO> getById(@PathVariable("id") Integer id);
}
