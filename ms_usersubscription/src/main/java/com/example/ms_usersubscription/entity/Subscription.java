package com.example.ms_usersubscription.entity;

import com.example.ms_usersubscription.dto.ServiceDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    @OneToMany(mappedBy = "subscription", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;
}
