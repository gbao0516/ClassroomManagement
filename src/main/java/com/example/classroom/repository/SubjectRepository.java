package com.example.classroom.repository;

import com.example.classroom.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findbbySubjectcode(String subjectCode);


}
