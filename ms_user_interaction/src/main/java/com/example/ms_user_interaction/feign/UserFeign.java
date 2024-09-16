package com.example.ms_user_interaction.feign;

import com.example.ms_user_interaction.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="ms-usersubscription-service", path="/user")

public interface UserFeign {
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDto>> listById(@PathVariable Integer id);
}
