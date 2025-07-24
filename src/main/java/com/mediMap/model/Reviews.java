package com.mediMap.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;

@Entity
@Data
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private Users user;
    private double ratings;
    private String comment;

    @CreationTimestamp
    private LocalDate CreatedAt;
}
