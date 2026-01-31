package com.example.classroom.repository;

import com.example.classroom.model.Attendance;
import com.example.classroom.model.Session;
import com.example.classroom.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findBySessionAndStudent(Session session, Student student);

    List<Attendance> findBySessionSessionId(Long sessionId);
}
