package com.example.classroom.controller;

import com.example.classroom.DTO.Request.EnrollmentCreationRequest;
import com.example.classroom.DTO.Response.EnrollmentCreationResponse;
import com.example.classroom.model.Enrollment;
import com.example.classroom.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @PostMapping
    public EnrollmentCreationResponse createEnrollment(@RequestBody EnrollmentCreationRequest request) {
        return enrollmentService.createEnrollment(request);
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PutMapping("/{id}")
    public EnrollmentCreationResponse updateEnrollment(@PathVariable Long id,
            @RequestBody EnrollmentCreationRequest request) {
        return enrollmentService.updateEnrollment(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}
