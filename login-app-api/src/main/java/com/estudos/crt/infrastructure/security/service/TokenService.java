package com.estudos.crt.infrastructure.security.service;

import com.estudos.crt.entities.Usuario;
import com.estudos.crt.infrastructure.security.dtos.TokenDTO;

public interface TokenService {
    String generateToken(Usuario usuario, Integer expiration);
    TokenDTO obterTokens(Usuario usuario);
    String validateToken(String token);
}
