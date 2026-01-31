package com.example.classroom.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreationResponse {
    private Long studentId;
    private String studentCode;
    private String fullName;
    private LocalDate dob;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime createdAt;
}
