package com.example.classroom.DTO.Request;

import lombok.Data;

@Data
public class AttendanceMarkingRequest {
    private Long sessionId;
    private Long studentId;
    private String status; // PRESENT, ABSENT, etc.
}
