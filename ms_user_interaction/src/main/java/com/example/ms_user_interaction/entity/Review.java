package com.example.ms_user_interaction.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //user id (Comunicacion entre microservicios)
    //service id (Comunicacion entre microservicios)
    private Integer rating;
    private String review;
    private Date send_date;
    private Date update_date;
}
