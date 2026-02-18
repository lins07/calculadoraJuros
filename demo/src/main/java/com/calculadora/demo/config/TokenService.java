package com.calculadora.demo.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.calculadora.demo.model.Usuario;


@Service
public class TokenService {

    @Value("${api.security.token.secret:my-super-secret-key-with-more-than-32-characters}")

    private String secret;

    public String generateToken(Usuario userDetails) {

        try {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    String token = JWT.create()
            .withIssuer("CalculadoraDeJuros")
            .withSubject(((Usuario) userDetails).getEmail())
            .withExpiresAt(genExpiresAt())
            .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
        throw new RuntimeException("Error while generating token", exception);
        
        } 
    }

    public String ValidateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("CalculadoraDeJuros")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Instant genExpiresAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
