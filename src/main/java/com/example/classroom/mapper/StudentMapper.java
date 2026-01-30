package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.StudentCreationRequest;
import com.example.classroom.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public interface StudentMapper {
    public Student toStudent(StudentCreationRequest studentDTO);
}
