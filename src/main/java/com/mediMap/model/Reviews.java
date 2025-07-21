package com.mediMap.model;

import jakarta.persistence.*;
import lombok.Data;

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
    private String comment;
    private LocalDate CreatedAt;
}
