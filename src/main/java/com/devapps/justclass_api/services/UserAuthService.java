package com.devapps.justclass_api.services;

import com.devapps.justclass_api.models.user.User;
import com.devapps.justclass_api.models.user.UserAuthRequest;
import com.devapps.justclass_api.models.user.UserAuthResponse;
import com.devapps.justclass_api.models.user.UserRegisterRequest;
import com.devapps.justclass_api.repositories.UserRepository;
import com.devapps.justclass_api.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserAuthResponse register(UserRegisterRequest userRegisterRequest) {
        String username = userRegisterRequest.getUsername();
        String email = userRegisterRequest.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        try {
            var user = User.builder()
                    .username(userRegisterRequest.getUsername())
                    .email(userRegisterRequest.getEmail())
                    .role(userRegisterRequest.getRole())
                    .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                    .build();

            if (user.getUsername().contains("\n") ||
                    user.getEmail().contains("\n") ||
                    user.getPassword().contains("\n")) {
                throw new IllegalArgumentException("Invalid input: Newline characters are not allowed.");
            }

            userRepository.save(user);
            var jwtToken = jwtService.createToken(user);
            return UserAuthResponse.builder()
                    .token(jwtToken)
                    .role(userRegisterRequest.getRole())
                    .message(userRegisterRequest.getUsername() + "'s account has been created, proceed to login")
                    .build();
        } catch (Exception e) {
            return UserAuthResponse.builder()
                    .token(null)
                    .message("The following error occurred: " + e.getLocalizedMessage())
                    .build();
        }
    }

    public UserAuthResponse login(UserAuthRequest userAuthRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword())
            );

            var user = (User) userRepository.findByUsername(userAuthRequest.getUsername()).orElseThrow();
            var jwtToken = jwtService.createToken(user);
            return UserAuthResponse.builder()
                    .token(jwtToken)
                    .message("Welcome, " + userAuthRequest.getUsername())
                    .role(user.getRole())
                    .build();
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials: " + e.getLocalizedMessage());
        } catch (Exception e) {
            throw new RuntimeException("A problem occured: " + e.getLocalizedMessage());
        }
    }
}
