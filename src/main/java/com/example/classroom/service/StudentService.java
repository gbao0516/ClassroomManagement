package com.example.classroom.service;

import com.example.classroom.DTO.Request.StudentCreationRequest;
import com.example.classroom.DTO.Response.StudentCreationResponse;
import com.example.classroom.model.Student;
import com.example.classroom.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentCreationResponse createStudent(StudentCreationRequest request) {
        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setFullName(request.getFullName());
        student.setDob(request.getDob());
        student.setGender(request.getGender());
        student.setPhone(request.getPhone());
        student.setEmail(request.getEmail());
        student.setAddress(request.getAddress());
        student.setCreatedAt(java.time.LocalDateTime.now());

        Student savedStudent = studentRepository.save(student);
        return convertToResponse(savedStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public StudentCreationResponse updateStudent(Long id, StudentCreationRequest request) {
        Student existingStudent = getStudentById(id);
        if (existingStudent == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }

        existingStudent.setStudentCode(request.getStudentCode());
        existingStudent.setFullName(request.getFullName());
        existingStudent.setDob(request.getDob());
        existingStudent.setGender(request.getGender());
        existingStudent.setPhone(request.getPhone());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setAddress(request.getAddress());

        Student savedStudent = studentRepository.save(existingStudent);
        return convertToResponse(savedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
        studentRepository.deleteById(id);
    }

    private StudentCreationResponse convertToResponse(Student student) {
        StudentCreationResponse response = new StudentCreationResponse();
        response.setStudentId(student.getStudentId());
        response.setStudentCode(student.getStudentCode());
        response.setFullName(student.getFullName());
        response.setDob(student.getDob());
        response.setGender(student.getGender());
        response.setPhone(student.getPhone());
        response.setEmail(student.getEmail());
        response.setAddress(student.getAddress());
        response.setCreatedAt(student.getCreatedAt());

        return response;
    }
}
