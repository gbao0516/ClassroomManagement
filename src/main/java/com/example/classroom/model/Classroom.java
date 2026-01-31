package com.example.classroom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classes")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    @Column(name = "class_code", nullable = false, unique = true)
    private String classCode;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "term", nullable = false)
    private String term;

    @Column(name = "start_date")
    private java.time.LocalDate startDate;

    @Column(name = "end_date")
    private java.time.LocalDate endDate;

    @Column(name = "room")
    private String room;

    @Column(name = "max_students", nullable = false)
    private int maxStudents;

    @Column(name = "status", nullable = false)
    private String status; // OPEN / CLOSED

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDateTime createdAt;
}
