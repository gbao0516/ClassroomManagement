package com.example.classroom.service;

import com.example.classroom.DTO.Request.TeacherCreationRequest;
import com.example.classroom.DTO.Response.TeacherCreationResponse;
import com.example.classroom.model.Teacher;
import com.example.classroom.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public TeacherCreationResponse createTeacher(TeacherCreationRequest request) {
        // Create new Teacher entity
        Teacher teacher = new Teacher();
        teacher.setTeacherCode(request.getTeacherCode());
        teacher.setFullName(request.getFullName());
        teacher.setPhone(request.getPhone());
        teacher.setEmail(request.getEmail());
        teacher.setAddress(request.getAddress());
        teacher.setCreatedAt(java.time.LocalDateTime.now());

        // Save to database
        Teacher savedTeacher = teacherRepository.save(teacher);

        // Convert to response DTO
        return convertToResponse(savedTeacher);
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public TeacherCreationResponse updateTeacher(Long id, TeacherCreationRequest request) {
        Teacher existingTeacher = getTeacherById(id);
        if (existingTeacher == null) {
            throw new IllegalArgumentException("Teacher with ID " + id + " not found.");
        }

        existingTeacher.setTeacherCode(request.getTeacherCode());
        existingTeacher.setFullName(request.getFullName());
        existingTeacher.setPhone(request.getPhone());
        existingTeacher.setEmail(request.getEmail());
        existingTeacher.setAddress(request.getAddress());
        // Do NOT update createdAt usually

        Teacher savedTeacher = teacherRepository.save(existingTeacher);
        return convertToResponse(savedTeacher);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new IllegalArgumentException("Teacher with ID " + id + " not found.");
        }
        teacherRepository.deleteById(id);
    }

    private TeacherCreationResponse convertToResponse(Teacher savedTeacher) {
        TeacherCreationResponse response = new TeacherCreationResponse();
        response.setTeacherId(savedTeacher.getTeacherId());
        response.setTeacherCode(savedTeacher.getTeacherCode());
        response.setFullName(savedTeacher.getFullName());
        response.setAddress(savedTeacher.getAddress());
        response.setEmail(savedTeacher.getEmail());
        response.setPhone(savedTeacher.getPhone());
        response.setCreatedAt(savedTeacher.getCreatedAt());

        return response;
    }
}
