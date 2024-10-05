package com.estudos.crt.infrastructure.security.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.estudos.crt.entities.Usuario;
import com.estudos.crt.infrastructure.security.dtos.TokenDTO;
import com.estudos.crt.infrastructure.security.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${auth.jtw.token.secret}")
    private String secretKey;

    @Value("${auth.jwt.token.expiration}")
    private Integer tokenExpiration;

    @Value("${auth.jwt.refresh-token.expiration}")
    private Integer RefreshTokenExpiration;

    @Override
    public String generateToken(Usuario usuario, Integer expiration) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("login-app")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(expirationDate(expiration))
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public TokenDTO obterTokens(Usuario usuario) {
        return TokenDTO.builder()
                .token(generateToken(usuario, tokenExpiration))
                .refreshToken(generateToken(usuario, RefreshTokenExpiration))
                .build();
    }

    @Override
    public String validateToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.require(algorithm)
                .withIssuer("login-app")
                .build()
                .verify(token)
                .getSubject();
    }

    private Instant expirationDate(Integer expiration) {
        return LocalDateTime.now().plusSeconds(expiration).toInstant(ZoneOffset.of("-03:00"));
    }

}
