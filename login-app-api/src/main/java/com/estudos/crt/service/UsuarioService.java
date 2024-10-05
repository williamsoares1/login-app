package com.estudos.crt.service;

import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.estudos.crt.dtos.request.UsuarioLoginRequestDTO;
import com.estudos.crt.dtos.request.UsuarioRegisterRequestDTO;
import com.estudos.crt.dtos.response.UsuarioResponseDTO;
import com.estudos.crt.entities.Usuario;

public interface UsuarioService {
    Optional<UsuarioResponseDTO> login(UsuarioLoginRequestDTO dto);

    Optional<String> register(UsuarioRegisterRequestDTO dto);

    void refreshToken() throws NotFoundException;

    void salvarTokens(Usuario usuario);

    void logout();
}