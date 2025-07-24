package com.mediMap.dto;

import lombok.Data;

import java.util.List;

@Data
public class HospitalDto {

    private String hospitalName;
    private String email;
    private String location;
    private String description;
    private String image;
    private List<String> specialties;
    private Boolean emergency;
    private String openingHours;
    private String website;
    private Double ratings;
    private Double lat;
    private Double lng;
}
