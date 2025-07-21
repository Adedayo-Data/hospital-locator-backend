package com.mediMap.controller;

import com.mediMap.dto.AuthMessage;
import com.mediMap.dto.UserRequestDTO;
import com.mediMap.repository.UserRepositoy;
import com.mediMap.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {

    private UserService service;
    @PostMapping("/register")
    public ResponseEntity<AuthMessage> signup(@RequestBody UserRequestDTO requestDTO){

        return null;
    }
}
