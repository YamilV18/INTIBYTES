package com.example.ms_usersubscription.feign;

import com.example.ms_usersubscription.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-services-service")
public interface CategoryFeign {
    @GetMapping("/categories/{id}")
    ResponseEntity<CategoryDTO> getById(@PathVariable("id") Integer id);
}
