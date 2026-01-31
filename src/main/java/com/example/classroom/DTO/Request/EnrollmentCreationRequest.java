package com.example.classroom.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentCreationRequest {
    private Long classId;
    private Long studentId;
    private String enrollStatus; // ACTIVE / DROPPED
}
