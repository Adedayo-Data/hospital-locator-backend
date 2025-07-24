package com.mediMap.security;


import com.mediMap.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Filter chain entered!");

        String token = "";
        String username = "";

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println("condition passed");
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        System.out.println("username is: " + username);
        System.out.println("token is: " +token);
        if(!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null){
            // Validate the token
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

            if(jwtService.validateToken(token, userDetails)){

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // set it in securityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authToken);

                System.out.println("UserDetails role: "+ userDetails.getAuthorities());
            }

        }
        System.out.println("Leaving JwtFilter chain");
        filterChain.doFilter(request, response);
    }
}
