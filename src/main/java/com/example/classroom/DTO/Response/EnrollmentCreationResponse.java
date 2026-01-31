package com.example.classroom.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentCreationResponse {
    private Long enrollmentId;
    private Long classId;
    private Long studentId;
    private String enrollStatus;
    private LocalDateTime enrolledAt;
}
