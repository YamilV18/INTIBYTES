package com.example.msbillingreports.Feign;

import com.example.msbillingreports.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="ms-usersubscription-service-user", path="/user")
public interface UserFeign {
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> listById(@PathVariable Integer id);
}
