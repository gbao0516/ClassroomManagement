package com.example.classroom.controller;
import com.example.classroom.DTO.Request.LoginRequest;
import com.example.classroom.DTO.Request.RefreshTokenRequest;
import com.example.classroom.DTO.Request.RegisterRequest;
import com.example.classroom.DTO.Response.AuthResponse;
import com.example.classroom.DTO.Response.MessageResponse;
import com.example.classroom.DTO.Response.UserResponse;
import com.example.classroom.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins:*}", maxAge = 3600)
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Registration request received for username: {}", request.getUsername());
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates user and returns JWT tokens")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("Login request received for username: {}", request.getUsername());
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token", description = "Generates new access token using refresh token")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        log.debug("Token refresh request received");
        AuthResponse response = authService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Returns information about the authenticated user")
    public ResponseEntity<UserResponse> getCurrentUser() {
        log.debug("Get current user request received");
        UserResponse response = authService.getCurrentUser();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/logout")
    @Operation(summary = "User logout", description = "Logs out the current user")
    public ResponseEntity<MessageResponse> logout() {
        log.info("Logout request received");
        MessageResponse response = authService.logout();
        return ResponseEntity.ok(response);
    }
}