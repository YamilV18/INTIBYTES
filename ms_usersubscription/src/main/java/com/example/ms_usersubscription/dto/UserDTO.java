package com.example.ms_usersubscription.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String email;
    private String role;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private ServiceDTO service;
}
