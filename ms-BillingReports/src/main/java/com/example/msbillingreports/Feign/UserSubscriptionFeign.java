package com.example.msbillingreports.Feign;

import com.example.msbillingreports.Dto.SubscriptionDto;
import com.example.msbillingreports.Dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-usersubscription-service", path = "/")
public interface UserSubscriptionFeign {
    @GetMapping("/subscription/{id}")
    @CircuitBreaker(name = "subscriptionListByIdCB", fallbackMethod = "subscriptionListById")
    public ResponseEntity<SubscriptionDto> listSubById(@PathVariable Integer id);
    default ResponseEntity<SubscriptionDto> subscriptionListById(Integer id, Exception e) {
        return ResponseEntity.ok(new SubscriptionDto());
    }

    @GetMapping("/user/{id}")
    @CircuitBreaker(name = "userListByIdCB", fallbackMethod = "userListById")
    public ResponseEntity<UserDto> listUserById(@PathVariable Integer id);
    default ResponseEntity<UserDto> userListById(Integer id, Exception e) {
        return ResponseEntity.ok(new UserDto());
    }

}