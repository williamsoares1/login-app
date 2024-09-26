package com.estudos.crt.infrastructure.security.dtos;

import lombok.Builder;

@Builder
public record TokenRefreshDTO(String refreshToken) {

}
