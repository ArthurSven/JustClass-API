package com.devapps.justclass_api.controllers;

import com.devapps.justclass_api.models.user.UserAuthRequest;
import com.devapps.justclass_api.models.user.UserAuthResponse;
import com.devapps.justclass_api.models.user.UserRegisterRequest;
import com.devapps.justclass_api.services.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping(value = "/auth/user")
public class AuthController {

    private final UserAuthService userAuthService;

    public AuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserAuthResponse> register(@RequestBody UserRegisterRequest request) {
        try {
            return ResponseEntity.ok(userAuthService.register(request));
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(UserAuthResponse.builder()
                            .token(null)
                            .message(ex.getReason())
                            .build());
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserAuthRequest request) {
        try {
            return ResponseEntity.ok(userAuthService.login(request));
        } catch (ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(UserAuthResponse.builder()
                            .token(null)
                            .message(ex.getReason())
                            .build()
                    );
        }

    }


}
