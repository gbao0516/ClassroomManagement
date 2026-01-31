package com.example.classroom.controller;

import com.example.classroom.DTO.Request.SessionCreationRequest;
import com.example.classroom.DTO.Response.SessionResponse;
import com.example.classroom.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @GetMapping
    public List<SessionResponse> getAllSessions(@RequestParam(required = false) Long classId) {
        if (classId != null) {
            return sessionService.getSessionsByClass(classId);
        }
        return sessionService.getAllSessions();
    }

    @PostMapping
    public SessionResponse createSession(@RequestBody SessionCreationRequest request) {
        return sessionService.createSession(request);
    }
}
