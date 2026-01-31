package com.example.classroom.repository;

import com.example.classroom.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findbbyEnrollmentId(String enrollmentCode);
}
