package com.example.classroom.service;

import com.example.classroom.DTO.Request.TeacherCreationRequest;
import com.example.classroom.DTO.Request.TeacherCreationResponse;
import com.example.classroom.mapper.TeacherMapper;
import com.example.classroom.model.Teacher;
import com.example.classroom.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherService {
    private final TeacherRepository  teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    public TeacherCreationResponse createTeacher(TeacherCreationRequest teacher) {
        Teacher st = getTeacherById(teacher.getTeacherId());
        if (st != null) {
            throw new IllegalArgumentException("Teacher with ID " + teacher.getTeacherId() + " already exists.");
        }

        teacherRepository.save(st);
        return convertToResponse(st);
    }
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }
    public Teacher updateTeacher(Long id, TeacherCreationRequest updatedTeacher) {
        Teacher existingTeacher = getTeacherById(id);
        if (existingTeacher == null) {
            throw new IllegalArgumentException("Teacher with ID " + id + " not found.");
        }

        Teacher sm = teacherMapper.toTeacher(updatedTeacher);
        return teacherRepository.save(sm);
    }
    public void deleteTeacher(Long id) {
        Teacher existingTeacher = getTeacherById(id);
        if (existingTeacher == null) {
            throw new IllegalArgumentException("Teacher with ID " + id + " not found.");
        }
        teacherRepository.deleteById(id);
    }

    private TeacherCreationResponse convertToResponse(Teacher savedTeacher) {
        TeacherCreationResponse response = new TeacherCreationResponse();
        response.setTeacherId(savedTeacher.getTeacherId());
        response.setFullName(savedTeacher.getFullName());
        response.setAddress(savedTeacher.getAddress());
        response.setEmail(savedTeacher.getEmail());
        response.setDob(savedTeacher.getDob());

        return response;
    }


}
