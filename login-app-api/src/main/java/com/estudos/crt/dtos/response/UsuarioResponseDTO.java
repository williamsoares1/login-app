package com.estudos.crt.dtos.response;

import lombok.Builder;

@Builder
public record UsuarioResponseDTO(Long id, String nome, String email, String cargo) {

}