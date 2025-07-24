package com.mediMap.service;

import com.mediMap.dto.AuthMessage;
import com.mediMap.dto.LoginRequest;
import com.mediMap.dto.UserRequestDTO;
import com.mediMap.model.ROLE;
import com.mediMap.model.Users;
import com.mediMap.repository.UserRepositoy;
import com.mediMap.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepositoy userRepo;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthMessage saveUser(UserRequestDTO requestDTO){
        Users user = new Users();
        user.setFullName(requestDTO.getFullName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRole(ROLE.valueOf(requestDTO.getRole()));
        user.setCreatedAt(LocalDate.now());

        System.out.println("User object is: " + user);

        Users newUser = userRepo.save(user);
        System.out.println("New User object is: " + newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(user);
        String message = "Registration successful";

        return new AuthMessage(jwt, message, user.getRole());
    }

    public AuthMessage validateUser(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String loginPassword = loginRequest.getPassword();

        System.out.println("email is: " + email);
        System.out.println("User Password: " + loginPassword);
        Users user = userRepo.findByEmail(email);
        System.out.println("User from db" + user.getPassword());

        if(user == null){
            throw new UsernameNotFoundException("No user with this email Address!");
        }

        if(loginPassword.matches(user.getPassword())){
            System.out.println("Condition passed");
            String jwt = jwtService.generateToken(user);
            return new AuthMessage(jwt, "Login Successful", user.getRole());
        }

        return null;
    }
    // login service
    // user provides some details - payload
    // verify the payload....
    // check if email is in db
    // if email is there, validate the password
    // if everything is ok return a positive AuthResponse

}
