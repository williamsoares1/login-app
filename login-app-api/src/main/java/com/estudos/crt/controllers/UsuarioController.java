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
import com.estudos.crt.util.mapper.MapperS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/session")
    public UsuarioResponseDTO session(HttpSession session) throws JsonMappingException, JsonProcessingException {
        String userJson = (String) session.getAttribute("user");
        return MapperS.INSTANCE.StringToDTO(userJson);
    }

    @GetMapping("/teste")
    public String teste() {
        return "Foi";
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioLoginRequestDTO dto){
        return ResponseEntity.of(service.login(dto));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(){
        service.logout();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UsuarioRegisterRequestDTO dto){
        try{
            return ResponseEntity.ok(service.register(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro :" + e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(){
        service.refreshToken();
        return ResponseEntity.ok().build();
    }
    
}
