package com.example.classroom.controller;

import com.example.classroom.DTO.Request.SubjectCreationRequest;
import com.example.classroom.DTO.Request.SubjectCreationResponse;
import com.example.classroom.model.Subject;
import com.example.classroom.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping
    public SubjectCreationResponse createSubject(SubjectCreationRequest request) {
        return subjectService.createSubject(request);
    }

    @GetMapping("/{id}")
    public Subject getSubjectById( @PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject( @PathVariable Long id) {
        subjectService.deleteSubject(id);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id,@RequestBody SubjectCreationRequest request) {
        return subjectService.updateSubject(id, request);
    }


}
