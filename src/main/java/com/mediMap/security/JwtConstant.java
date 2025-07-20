package com.mediMap.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtConstant {

    @Value("${jwt-key}")
    public static String JWTKEY;

    public static final String HEADER = "Authorization";
}
