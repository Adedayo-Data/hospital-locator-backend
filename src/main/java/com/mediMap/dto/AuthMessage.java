package com.mediMap.dto;

import com.mediMap.model.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthMessage {

    private String token;
    private String message;
    private ROLE role;
}
