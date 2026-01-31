package com.example.classroom.service;

import com.example.classroom.DTO.Request.EnrollmentCreationRequest;
import com.example.classroom.DTO.Response.EnrollmentCreationResponse;
import com.example.classroom.model.Classroom;
import com.example.classroom.model.Enrollment;
import com.example.classroom.model.Student;
import com.example.classroom.repository.ClassroomRepository;
import com.example.classroom.repository.EnrollmentRepository;
import com.example.classroom.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
            ClassroomRepository classroomRepository,
            StudentRepository studentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public EnrollmentCreationResponse createEnrollment(EnrollmentCreationRequest request) {
        // Create new Enrollment entity
        Enrollment enrollment = new Enrollment();

        // Load Classroom and Student from repositories
        Classroom classroom = classroomRepository.findById(request.getClassId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Classroom with ID " + request.getClassId() + " not found."));
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Student with ID " + request.getStudentId() + " not found."));

        enrollment.setClassEntity(classroom);
        enrollment.setStudent(student);
        enrollment.setEnrollStatus(request.getEnrollStatus() != null ? request.getEnrollStatus() : "ACTIVE");
        enrollment.setEnrolledAt(java.time.LocalDateTime.now());

        // Save to database
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        // Convert to response DTO
        return convertToResponse(savedEnrollment);
    }

    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    public EnrollmentCreationResponse updateEnrollment(Long id, EnrollmentCreationRequest request) {
        Enrollment existingEnrollment = getEnrollmentById(id);
        if (existingEnrollment == null) {
            throw new IllegalArgumentException("Enrollment with ID " + id + " not found.");
        }

        // Update status (chỉ cho phép update status, không update class/student)
        existingEnrollment.setEnrollStatus(request.getEnrollStatus());

        Enrollment updatedEnrollment = enrollmentRepository.save(existingEnrollment);
        return convertToResponse(updatedEnrollment);
    }

    public void deleteEnrollment(Long id) {
        Enrollment existingEnrollment = getEnrollmentById(id);
        if (existingEnrollment == null) {
            throw new IllegalArgumentException("Enrollment with ID " + id + " not found.");
        }
        enrollmentRepository.deleteById(id);
    }

    private EnrollmentCreationResponse convertToResponse(Enrollment enrollment) {
        EnrollmentCreationResponse response = new EnrollmentCreationResponse();
        response.setEnrollmentId(enrollment.getEnrollmentId());
        response.setClassId(enrollment.getClassEntity().getClassId());
        response.setStudentId(enrollment.getStudent().getStudentId());
        response.setEnrollStatus(enrollment.getEnrollStatus());
        response.setEnrolledAt(enrollment.getEnrolledAt());

        return response;
    }
}
