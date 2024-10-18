package com.example.msbillingreports.Entity;

import com.example.msbillingreports.Dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date generation_date;
    private String content;

    private Integer userId;
    @Transient
    private UserDto user;
}
