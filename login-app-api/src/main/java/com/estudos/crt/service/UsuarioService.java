package com.estudos.crt.service;

import java.util.Optional;

import com.estudos.crt.dtos.request.UsuarioLoginRequestDTO;
import com.estudos.crt.dtos.request.UsuarioRegisterRequestDTO;
import com.estudos.crt.dtos.response.UsuarioResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UsuarioService {
    Optional<UsuarioResponseDTO> login(UsuarioLoginRequestDTO dto, HttpServletRequest request,
            HttpServletResponse response);
    
    void logout(HttpServletResponse response);

    Optional<String> register(UsuarioRegisterRequestDTO dto);

    Optional<UsuarioResponseDTO> obterRefreshToken(HttpServletRequest request, HttpServletResponse response);
}