package com.example.classroom.controller;

import com.example.classroom.DTO.Request.StudentCreationRequest;
import com.example.classroom.DTO.Request.StudentCreationResponse;
import com.example.classroom.model.Student;
import com.example.classroom.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public StudentCreationResponse createStudent(StudentCreationRequest request) {
        return studentService.createStudent(request);
    }

    @GetMapping("/{id}")
    public Student getStudentById( @PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent( @PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody StudentCreationRequest request) {
        return studentService.updateStudent(id, request);
    }


}
