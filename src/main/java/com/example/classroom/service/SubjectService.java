package com.example.classroom.service;

import com.example.classroom.DTO.Request.SubjectCreationRequest;
import com.example.classroom.DTO.Response.SubjectCreationResponse;
import com.example.classroom.model.Subject;
import com.example.classroom.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public SubjectCreationResponse createSubject(SubjectCreationRequest request) {
        Subject subject = new Subject();
        subject.setSubjectCode(request.getSubjectCode());
        subject.setSubjectName(request.getSubjectName());
        subject.setCredits(request.getCredits());
        subject.setDescription(request.getDescription());

        Subject savedSubject = subjectRepository.save(subject);
        return convertToResponse(savedSubject);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public SubjectCreationResponse updateSubject(Long id, SubjectCreationRequest request) {
        Subject existingSubject = getSubjectById(id);
        if (existingSubject == null) {
            throw new IllegalArgumentException("Subject with ID " + id + " not found.");
        }

        existingSubject.setSubjectCode(request.getSubjectCode());
        existingSubject.setSubjectName(request.getSubjectName());
        existingSubject.setCredits(request.getCredits());
        existingSubject.setDescription(request.getDescription());

        Subject savedSubject = subjectRepository.save(existingSubject);
        return convertToResponse(savedSubject);
    }

    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new IllegalArgumentException("Subject with ID " + id + " not found.");
        }
        subjectRepository.deleteById(id);
    }

    private SubjectCreationResponse convertToResponse(Subject subject) {
        SubjectCreationResponse response = new SubjectCreationResponse();
        response.setSubjectId(subject.getSubjectId());
        response.setSubjectCode(subject.getSubjectCode());
        response.setSubjectName(subject.getSubjectName());
        response.setCredits(subject.getCredits());
        response.setDescription(subject.getDescription());
        return response;
    }
}
