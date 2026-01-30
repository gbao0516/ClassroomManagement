package com.example.classroom.controller;

import com.example.classroom.DTO.Request.TeacherCreationRequest;
import com.example.classroom.DTO.Request.TeacherCreationResponse;
import com.example.classroom.model.Teacher;
import com.example.classroom.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class TeacherController {
    private TeacherService studentService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return studentService.getAllTeachers();
    }

    @PostMapping
    public TeacherCreationResponse createTeacher(TeacherCreationRequest request) {
        return studentService.createTeacher(request);
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById( @PathVariable Long id) {
        return studentService.getTeacherById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher( @PathVariable Long id) {
        studentService.deleteTeacher(id);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id,@RequestBody TeacherCreationRequest request) {
        return studentService.updateTeacher(id, request);
    }


}
