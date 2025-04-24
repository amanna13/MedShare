package com.medshare.app_backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtility {
    private final String secretKey = "jr4kh3j634j643n6346nj63nq";
    private final long expirationTime = 1000 * 60 * 60 * 24;

    public String generateToken(String userName) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("auth0")
                    .withSubject(userName)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error getting JWT Token, Token not issues ", e);
        }
    }


    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("auth0").build();
            DecodedJWT decodedJWT =  jwtVerifier.verify(token);
            return decodedJWT.getExpiresAt().after(new Date());
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUserName(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer("auth0").build();
            return jwtVerifier.verify(token).getSubject();
        } catch (JWTVerificationException e){
            return null;
        }
    }
}
