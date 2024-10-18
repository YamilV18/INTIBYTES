package com.example.msbillingreports.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubscriptionDto {
    private Integer id;
    private Date starDate;
    private Date endDate;
    private Status status;
    public enum Status {inactivo,activo}
    private UserDto user;
    private ServiceDto service;
}
