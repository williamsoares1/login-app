package com.estudos.crt.dtos.response;

import com.estudos.crt.entities.ENUM.CARGO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public record UsuarioResponseDTO(Long id, String nome, String email, @Enumerated(EnumType.STRING) CARGO cargo) {

}