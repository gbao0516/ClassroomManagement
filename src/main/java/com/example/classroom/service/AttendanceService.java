package com.example.classroom.service;

import com.example.classroom.DTO.Request.AttendanceMarkingRequest;
import com.example.classroom.DTO.Response.AttendanceResponse;
import com.example.classroom.model.Attendance;
import com.example.classroom.model.Enrollment;
import com.example.classroom.model.Session;
import com.example.classroom.model.Student;
import com.example.classroom.repository.AttendanceRepository;
import com.example.classroom.repository.EnrollmentRepository;
import com.example.classroom.repository.SessionRepository;
import com.example.classroom.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public AttendanceResponse markAttendance(AttendanceMarkingRequest request) {
        Session session = sessionRepository.findById(request.getSessionId())
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Check Enrollment
        Enrollment enrollment = enrollmentRepository.findByClassroomAndStudent(session.getClassroom(), student)
                .orElseThrow(() -> new IllegalArgumentException("Student is NOT enrolled in this class."));

        if (!"ACTIVE".equals(enrollment.getEnrollStatus())) {
            throw new IllegalArgumentException("Student enrollment is NOT ACTIVE. Cannot mark attendance.");
        }

        // Upsert Attendance
        Attendance attendance = attendanceRepository.findBySessionAndStudent(session, student)
                .orElse(new Attendance());

        if (attendance.getAttendanceId() == null) {
            attendance.setSession(session);
            attendance.setStudent(student);
        }

        attendance.setStatus(request.getStatus());
        attendance.setMarkedAt(LocalDateTime.now());

        Attendance saved = attendanceRepository.save(attendance);
        return convertToResponse(saved);
    }

    public List<AttendanceResponse> getAttendanceBySession(Long sessionId) {
        return attendanceRepository.findBySessionSessionId(sessionId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private AttendanceResponse convertToResponse(Attendance att) {
        AttendanceResponse res = new AttendanceResponse();
        res.setAttendanceId(att.getAttendanceId());
        res.setSessionId(att.getSession().getSessionId());
        res.setSessionTopic(att.getSession().getTopic());
        res.setStudentId(att.getStudent().getStudentId());
        res.setStudentName(att.getStudent().getFullName());
        res.setStudentCode(att.getStudent().getStudentCode());
        res.setStatus(att.getStatus());
        res.setMarkedAt(att.getMarkedAt());
        return res;
    }
}
