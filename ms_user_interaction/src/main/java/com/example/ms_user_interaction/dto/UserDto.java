package com.example.ms_user_interaction.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String email;
    private String password;

    private Role role;
    private Date starDate;
    private Date endDate;
    private Boolean status;
    public enum Role {administrador,colaborador, invitado}
}
