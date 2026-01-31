package com.example.classroom.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCreationRequest {
    private String studentCode;
    private String fullName;
    private LocalDate dob;
    private String gender;
    private String phone;
    private String email;
    private String address;
}
