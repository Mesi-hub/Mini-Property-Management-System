package edu.miu.cs545.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    @GetMapping
    public ResponseEntity<Void> get(){
        return ResponseEntity.ok().build();
    }
}
