package com.mediMap.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;
    private String password;
    private ROLE role;
    private LocalDate createdAt;

}
