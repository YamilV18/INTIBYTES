package com.example.ms_usersubscription.entity;

import com.example.ms_usersubscription.dto.ServiceDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date starDate;
    private Date endDate;
    private Status status;
    public enum Status {inactivo,activo}
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    private Integer serviceId;
    @Transient
    private ServiceDto service;
}
