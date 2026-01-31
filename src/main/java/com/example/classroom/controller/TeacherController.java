package com.example.classroom.controller;

import com.example.classroom.DTO.Request.TeacherCreationRequest;
import com.example.classroom.DTO.Response.TeacherCreationResponse;
import com.example.classroom.model.Teacher;
import com.example.classroom.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping
    public TeacherCreationResponse createTeacher(@RequestBody TeacherCreationRequest request) {
        return teacherService.createTeacher(request);
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable Long id) {
        return teacherService.getTeacherById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }

    @PutMapping("/{id}")
    public TeacherCreationResponse updateTeacher(@PathVariable Long id, @RequestBody TeacherCreationRequest request) {
        return teacherService.updateTeacher(id, request);
    }
}
