package com.mediMap.dto;

import lombok.Data;

@Data
public class ReviewsDto {

    private Long hospital_id;
    private Long user_id;
    private double ratings;
    private String comment;
}
