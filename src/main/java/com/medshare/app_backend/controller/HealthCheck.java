package com.medshare.app_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public ResponseEntity<String> healthcheck(){
        return new ResponseEntity<>("Server is up and Running !", HttpStatus.OK);

    }
}
