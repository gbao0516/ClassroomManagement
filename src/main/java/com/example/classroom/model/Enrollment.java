package com.example.classroom.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments", uniqueConstraints = @UniqueConstraint(columnNames = { "classroom_id", "student_id" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classroom classEntity;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "enroll_status", nullable = false)
    private String enrollStatus; // ACTIVE / DROPPED

    @Column(name = "enrolled_at", nullable = false)
    private LocalDateTime enrolledAt;
}
