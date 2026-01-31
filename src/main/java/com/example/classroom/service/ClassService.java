package com.example.classroom.service;

import com.example.classroom.DTO.Request.ClassroomCreationRequest;
import com.example.classroom.DTO.Response.ClassroomCreationResponse;
import com.example.classroom.mapper.ClassroomMapper;
import com.example.classroom.model.Classroom;
import com.example.classroom.model.Subject;
import com.example.classroom.model.Teacher;
import com.example.classroom.repository.ClassroomRepository;
import com.example.classroom.repository.SubjectRepository;
import com.example.classroom.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    private final ClassroomRepository classroomRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    private ClassroomMapper ClassroomMapper;

    public ClassService(ClassroomRepository classroomRepository,
            SubjectRepository subjectRepository,
            TeacherRepository teacherRepository) {
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<Classroom> getAllClasss() {
        return classroomRepository.findAll();
    }

    public ClassroomCreationResponse createClassroom(ClassroomCreationRequest request) {
        // Create new Classroom entity
        Classroom classroom = new Classroom();
        classroom.setClassCode(request.getClassCode());
        classroom.setTerm(request.getTerm());
        classroom.setStartDate(request.getStartDate());
        classroom.setEndDate(request.getEndDate());
        classroom.setRoom(request.getRoom());
        classroom.setMaxStudents(request.getMaxStudents());
        classroom.setStatus(request.getStatus() != null ? request.getStatus() : "OPEN");
        classroom.setCreatedAt(java.time.LocalDateTime.now());

        // Load Subject and Teacher from repositories
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Subject with ID " + request.getSubjectId() + " not found."));
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Teacher with ID " + request.getTeacherId() + " not found."));

        classroom.setSubject(subject);
        classroom.setTeacher(teacher);

        // Save to database
        Classroom savedClassroom = classroomRepository.save(classroom);

        // Convert to response DTO
        return convertToResponse(savedClassroom);
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
        response.setClassCode(savedClassroom.getClassCode());
        response.setTerm(savedClassroom.getTerm());
        response.setStatus(savedClassroom.getStatus());
        response.setMaxStudents(savedClassroom.getMaxStudents());

        return response;
    }

}
