package com.example.ms_user_interaction.feign;

import com.example.ms_user_interaction.dto.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name="ms-usersubscription-service", path="/user")

public interface UserFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "userListByIdCB", fallbackMethod = "userListById")
    public ResponseEntity<UserDto> listById(@PathVariable Integer id);


    // Puedes eliminar el método duplicado o corregirlo si tiene otra funcionalidad
    // Si es redundante, quítalo o cambia el nombre si tiene una lógica diferente
    @GetMapping("/{id}") // Falta esta anotación para corregir el error
    ResponseEntity<UserDto> getById(@PathVariable Integer id);

    // Metodo por defecto en caso de fallback
    default ResponseEntity<UserDto> userListById(Integer id, Exception e) {
        return ResponseEntity.ok(new UserDto()); // Lógica de fallback
    }
}
