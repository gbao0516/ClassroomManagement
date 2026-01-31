package com.example.classroom.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectCreationResponse {
    private Long subjectId;
    private String subjectCode;
    private String subjectName;
    private int credits;
    private String description;
}
