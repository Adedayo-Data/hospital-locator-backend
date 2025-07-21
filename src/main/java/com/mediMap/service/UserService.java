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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepositoy userRepo;
    private JwtService jwtService;

    public AuthMessage saveUser(UserRequestDTO requestDTO){
        Users user = new Users();
        user.setFullName(requestDTO.getFullName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setRole(ROLE.valueOf(requestDTO.getRole()));

        user = userRepo.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateToken(user);
        String message = "Registration successful";

        return new AuthMessage(jwt, message, user.getRole());
    }

    public AuthMessage validateUser(LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String loginPassword = loginRequest.getPassword();
        Users user = userRepo.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("No user with this email Address!");
        }

        if(loginPassword.matches(user.getPassword())){
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
