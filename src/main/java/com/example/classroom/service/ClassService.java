package com.example.classroom.service;

import com.example.classroom.DTO.Request.ClassroomCreationRequest;
import com.example.classroom.DTO.Request.ClassroomCreationRequest;
import com.example.classroom.DTO.Request.ClassroomCreationResponse;
import com.example.classroom.DTO.Request.ClassroomCreationResponse;
import com.example.classroom.mapper.ClassroomMapper;
import com.example.classroom.model.Classroom;
import com.example.classroom.model.Classroom;
import com.example.classroom.model.Classroom;
import com.example.classroom.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassService {
    private final ClassroomRepository classroomRepository;

    @Autowired
    private ClassroomMapper ClassroomMapper;

    public ClassService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }
    public List<Classroom> getAllClasss() {
        return classroomRepository.findAll();
    }



    public ClassroomCreationResponse createClassroom(ClassroomCreationRequest classroom) {
        Classroom st = getClassById(classroom.getClassId());
        if (st != null) {
            throw new IllegalArgumentException("Classroom with ID " + classroom.getClassId() + " already exists.");
        }

        classroomRepository.save(st);
        return convertToResponse(st);
    }
    public Classroom getClassById(Long id) {
        return classroomRepository.findById(id).orElse(null);
    }
    public Classroom updateClass(Long id, ClassroomCreationRequest updatedClass) {
        Classroom existingClassroom = getClassById(id);
        if (existingClassroom == null) {
            throw new IllegalArgumentException("Classroom with ID " + id + " not found.");
        }

        Classroom sm = ClassroomMapper.toClassroom(updatedClass);
        return classroomRepository.save(sm);
    }
    public void deleteClass(Long id) {
        Classroom existingClassroom = getClassById(id);
        if (existingClassroom == null) {
            throw new IllegalArgumentException("Classroom with ID " + id + " not found.");
        }
        classroomRepository.deleteById(id);
    }
    private ClassroomCreationResponse convertToResponse(Classroom savedClassroom) {
        ClassroomCreationResponse response = new ClassroomCreationResponse();
        response.setClassId(savedClassroom.getClassId());
        response.setClassName(savedClassroom.getClassName());
        response.setCapacity(savedClassroom.getCapacity());

        return response;
    }



}
