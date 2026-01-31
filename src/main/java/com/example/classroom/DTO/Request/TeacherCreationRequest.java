package com.example.classroom.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCreationRequest {
    private String teacherCode;
    private String fullName;
    private String phone;
    private String email;
    private String address;
}
