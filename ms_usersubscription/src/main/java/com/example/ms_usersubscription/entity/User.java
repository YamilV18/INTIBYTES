package com.example.ms_usersubscription.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

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

    private Role role;
    private Date starDate;
    private Date endDate;
    private Boolean status;
    public enum Role {administrador,colaborador, invitado} //0=administrador, 1=colaborador, 2=invitado


}
