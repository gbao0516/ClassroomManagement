package com.example.classroom.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomCreationRequest {
    private String classCode;
    private Long subjectId;
    private Long teacherId;
    private String term;
    private LocalDate startDate;
    private LocalDate endDate;
    private String room;
    private int maxStudents;
    private String status; // OPEN / CLOSED
}
