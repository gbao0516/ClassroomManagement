package com.example.classroom.service;

import com.example.classroom.DTO.Request.StudentCreationRequest;
import com.example.classroom.DTO.Request.StudentCreationResponse;
import com.example.classroom.mapper.StudentMapper;
import com.example.classroom.model.Student;
import com.example.classroom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
private final StudentRepository  studentRepository;

@Autowired
private StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public StudentCreationResponse createStudent(StudentCreationRequest student) {
        Student st = getStudentById(student.getStudentId());
        if (st != null) {
            throw new IllegalArgumentException("Student with ID " + student.getStudentId() + " already exists.");
        }

        studentRepository.save(st);
        return convertToResponse(st);
    }
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    public Student updateStudent(Long id, StudentCreationRequest updatedStudent) {
        Student existingStudent = getStudentById(id);
        if (existingStudent == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }

        Student sm = studentMapper.toStudent(updatedStudent);
        return studentRepository.save(sm);
    }
    public void deleteStudent(Long id) {
        Student existingStudent = getStudentById(id);
        if (existingStudent == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
        studentRepository.deleteById(id);
    }

    private StudentCreationResponse convertToResponse(Student savedStudent) {
        StudentCreationResponse response = new StudentCreationResponse();
        response.setStudentId(savedStudent.getStudentId());
        response.setFullName(savedStudent.getFullName());
        response.setAddress(savedStudent.getAddress());
        response.setEmail(savedStudent.getEmail());
        response.setDob(savedStudent.getDob());

        return response;
    }


}
