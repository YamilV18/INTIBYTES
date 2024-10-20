package com.example.msbillingreports.Feign;

import com.example.msbillingreports.Dto.SubscriptionDto;
import com.example.msbillingreports.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-usersubscription-service", path = "/")
public interface UserSubscriptionFeign {
    @GetMapping("/subscription/{id}")
    public ResponseEntity<SubscriptionDto> listSubById(@PathVariable Integer id);

    @GetMapping("/{id}") // Falta esta anotación para corregir el error
    ResponseEntity<SubscriptionDto> getById(@PathVariable Integer id);

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> listUserById(@PathVariable Integer id);
}