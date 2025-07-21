package com.mediMap.dto;

import com.mediMap.model.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthMessage {

    private String token;
    private String message;
    private ROLE role;
}
