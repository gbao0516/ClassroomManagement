package com.example.classroom.DTO.Response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
public class SessionResponse {
    private Long sessionId;
    private Long classId;
    private String className;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String topic;
    private String note;
    private LocalDateTime createdAt;
}
