package com.mediMap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String hospitalName;
    private String email;
    private String Location;
    private String description;
    private String image;
    private List<String> specialties;
    private Boolean emergency;
    private String openingHours;
    private String website;
    private Double lat;
    private Double lng;
    private Double ratings;

    @CurrentTimestamp
    private LocalDate CreatedAt;

}
