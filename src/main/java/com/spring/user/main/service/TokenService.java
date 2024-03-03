package com.spring.user.main.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    private static final Logger log = LogManager.getLogger(TokenService.class);

    public String generarToken(String userId) {
        log.info("[generarToken] Se recibe el id "+ userId);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
        log.info("[generarToken] Se configura tiempo de expiración "+ jwtExpirationMs);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
