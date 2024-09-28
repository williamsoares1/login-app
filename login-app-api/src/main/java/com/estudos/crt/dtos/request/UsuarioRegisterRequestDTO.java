package com.estudos.crt.dtos.request;

import com.estudos.crt.entities.ENUM.CARGO;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UsuarioRegisterRequestDTO(String nome, String email, String senha, @Enumerated(EnumType.STRING) CARGO cargo) {

}
