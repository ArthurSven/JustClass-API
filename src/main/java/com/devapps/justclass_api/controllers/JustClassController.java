package com.devapps.justclass_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JustClassController {

    @GetMapping("/")
    public ResponseEntity<String> startJustClass() {
        return ResponseEntity.ok("Just Class is up and running");
    }
}
