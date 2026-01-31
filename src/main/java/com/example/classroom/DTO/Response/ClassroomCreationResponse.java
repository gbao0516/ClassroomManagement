package com.example.classroom.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomCreationResponse {
    private Long classId;
    private String classCode;
    private String term;
    private LocalDate startDate;
    private LocalDate endDate;
    private String room;
    private int maxStudents;
    private String status;
    private LocalDateTime createdAt;
}
