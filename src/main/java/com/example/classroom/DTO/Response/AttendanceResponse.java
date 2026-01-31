package com.example.classroom.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AttendanceResponse {
    private Long attendanceId;
    private Long sessionId;
    private String sessionTopic;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private String status;
    private LocalDateTime markedAt;
}
