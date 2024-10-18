package com.example.msbillingreports.Entity;

import com.example.msbillingreports.Dto.SubscriptionDto;
import com.example.msbillingreports.Dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;


@Entity
@Data
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal amount;
    private LocalDate issue_date;
    private LocalDate expiration_date;
    private String state;

    private Integer subscriptionId;
    @Transient
    private SubscriptionDto subscription;
}

