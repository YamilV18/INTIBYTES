package com.example.msbillingreports.Feign;

import com.example.msbillingreports.Dto.SubscriptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "ms-usersubscription-service", path = "/subscription")
public interface SubscriptionFeign {
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDto> listById(@PathVariable Integer id);
}
