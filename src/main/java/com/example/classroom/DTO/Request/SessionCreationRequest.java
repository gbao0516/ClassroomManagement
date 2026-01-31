package com.example.classroom.DTO.Request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SessionCreationRequest {
    private Long classId;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String topic;
    private String note;
}
