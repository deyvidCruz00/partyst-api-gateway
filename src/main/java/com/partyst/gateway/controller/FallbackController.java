package com.partyst.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/auth")
    public ResponseEntity<Map<String, Object>> authFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Authentication service is temporarily unavailable");
        response.put("status", 503);
        response.put("message", "Please try again later");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> apiFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "API service is temporarily unavailable");
        response.put("status", 503);
        response.put("message", "Please try again later");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/projects")
    public ResponseEntity<Map<String, Object>> projectsFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Projects service is temporarily unavailable");
        response.put("status", 503);
        response.put("message", "Please try again later");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, Object>> usersFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Users service is temporarily unavailable");
        response.put("status", 503);
        response.put("message", "Please try again later");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
