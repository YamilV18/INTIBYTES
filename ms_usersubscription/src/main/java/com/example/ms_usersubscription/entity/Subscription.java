package com.example.ms_usersubscription.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date starDate;
    private Date endDate;
    private Status status;
    private Integer userId;
    public enum Status {inactivo,activo}
}
