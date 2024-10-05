package com.estudos.crt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {

    @GetMapping("/teste")
    public String teste() {
        return "Foi";
    };

    @GetMapping("/interceptor")
    public ResponseEntity<String> interceptor() {
        return ResponseEntity.ok().build();
    };

}
