package com.example.classroom.service;

import com.example.classroom.DTO.Request.ClassroomCreationRequest;
import com.example.classroom.DTO.Request.ClassroomCreationResponse;
import com.example.classroom.DTO.Request.SubjectCreationRequest;
import com.example.classroom.DTO.Request.SubjectCreationResponse;

import com.example.classroom.mapper.SubjectMapper;
import com.example.classroom.model.Classroom;
import com.example.classroom.model.Subject;
import com.example.classroom.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper SubjectMapper;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }


    public SubjectCreationResponse createSubject(SubjectCreationRequest subject) {
        Subject st = getSubjectById(subject.getSubjectId());
        if (st != null) {
            throw new IllegalArgumentException("Subject with ID " + subject.getSubjectId() + " already exists.");
        }

       subjectRepository.save(st);
        return convertToResponse(st);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject updateSubject(Long id, SubjectCreationRequest updatedSubject) {
        Subject existingSubject = getSubjectById(id);
        if (existingSubject == null) {
            throw new IllegalArgumentException("Subject with ID " + id + " not found.");
        }

        Subject sm = SubjectMapper.toSubject(updatedSubject);
        return subjectRepository.save(sm);
    }

    public void deleteSubject(Long id) {
        Subject existingSubject = getSubjectById(id);
        if (existingSubject == null) {
            throw new IllegalArgumentException("Subject with ID " + id + " not found.");
        }
        subjectRepository.deleteById(id);
    }
    private SubjectCreationResponse convertToResponse(Subject savedSubject) {
        SubjectCreationResponse response = new SubjectCreationResponse();
        response.setSubjectId(savedSubject.getSubjectId());
        response.setSubjectName(savedSubject.getSubjectName());
        response.setDescription(savedSubject.getDescription());

        return response;
    }

}
