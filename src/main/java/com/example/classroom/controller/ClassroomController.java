package com.example.classroom.controller;

import com.example.classroom.DTO.Request.ClassroomCreationRequest;
import com.example.classroom.DTO.Request.ClassroomCreationResponse;
import com.example.classroom.model.Classroom;
import com.example.classroom.model.Classroom;
import com.example.classroom.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/classes")
public class ClassroomController {
    private ClassService classroomService;
    @GetMapping
    public List<Classroom> getAllClasses() {
        return classroomService.getAllClasss();

    }
    @PostMapping
    public ClassroomCreationResponse createClassroom(ClassroomCreationRequest request) {
        return classroomService.createClassroom(request);
    }

    @GetMapping("/{id}")
    public Classroom getClassroomById(@PathVariable Long id) {
        return classroomService.getClassById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteClassroom( @PathVariable Long id) {
        classroomService.deleteClass(id);
    }

    @PutMapping("/{id}")
    public Classroom updateClassroom(@PathVariable Long id,@RequestBody ClassroomCreationRequest request) {
        return classroomService.updateClass(id, request);
    }
}
