package com.mediMap.dto;

import com.mediMap.model.Hospital;
import com.mediMap.model.Users;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewResponseDTO {

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private Users user;
    private Double ratings;
    private String comment;

}
