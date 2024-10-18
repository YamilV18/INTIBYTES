package com.example.msbillingreports.Dto;

import lombok.Data;

@Data
public class ServiceDto {
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private CategoryDto category;
}
