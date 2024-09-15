package com.example.ms_usersubscription.dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private CategoryDTO category;

}
