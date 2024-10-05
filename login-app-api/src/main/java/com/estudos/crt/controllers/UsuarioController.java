package com.estudos.crt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.crt.dtos.request.UsuarioLoginRequestDTO;
import com.estudos.crt.dtos.request.UsuarioRegisterRequestDTO;
import com.estudos.crt.dtos.response.UsuarioResponseDTO;
import com.estudos.crt.service.SessionService;
import com.estudos.crt.service.UsuarioService;
import com.estudos.crt.util.mapper.MapperS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
@Log4j2
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/session")
    public ResponseEntity<Object> session() throws JsonMappingException, JsonProcessingException, NotFoundException {
        String userJson = (String) sessionService.getAttribute("user");
        return ResponseEntity.ok(MapperS.INSTANCE.StringToDTO(userJson));
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
    public ResponseEntity<Void> refreshToken() throws NotFoundException{
        service.refreshToken();
        return ResponseEntity.ok().build();
    }
    
}
