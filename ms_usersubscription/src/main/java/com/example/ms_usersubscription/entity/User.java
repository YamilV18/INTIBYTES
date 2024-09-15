package com.example.ms_usersubscription.entity;

import com.example.ms_usersubscription.dto.ServiceDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    @Transient
    private ServiceDTO service; // Transient because it will be populated via Feign
}