package com.example.classroom.controller;

import com.example.classroom.DTO.Request.AttendanceMarkingRequest;
import com.example.classroom.DTO.Response.AttendanceResponse;
import com.example.classroom.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping
    public AttendanceResponse markAttendance(@RequestBody AttendanceMarkingRequest request) {
        return attendanceService.markAttendance(request);
    }

    @GetMapping("/session/{sessionId}")
    public List<AttendanceResponse> getAttendanceBySession(@PathVariable Long sessionId) {
        return attendanceService.getAttendanceBySession(sessionId);
    }
}
