package com.example.classroom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "student_code", nullable = false)
    private String studentCode;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "dob")
    private java.time.LocalDate dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "address")
    private String address;

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDateTime createdAt;
}
