package com.estudos.crt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.crt.dtos.request.UsuarioLoginRequestDTO;
import com.estudos.crt.dtos.request.UsuarioRegisterRequestDTO;
import com.estudos.crt.dtos.response.UsuarioResponseDTO;
import com.estudos.crt.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioLoginRequestDTO dto, HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.of(service.login(dto, request, response));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<UsuarioResponseDTO> refreshToken(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.of(service.obterRefreshToken(request, response));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UsuarioRegisterRequestDTO dto){
        try{
            return ResponseEntity.ok(service.register(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
        }
    }

    @GetMapping("/teste")
    public String teste() {
        return "Foi";
    }
    
}
