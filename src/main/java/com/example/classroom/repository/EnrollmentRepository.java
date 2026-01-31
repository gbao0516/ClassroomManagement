package com.example.classroom.repository;

import com.example.classroom.model.Classroom;
import com.example.classroom.model.Enrollment;
import com.example.classroom.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByClassroomClassId(Long classId);

    List<Enrollment> findByStudentStudentId(Long studentId);

    Optional<Enrollment> findByClassroomAndStudent(Classroom classroom, Student student);
}
