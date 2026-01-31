package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.StudentCreationRequest;
import com.example.classroom.DTO.Response.StudentCreationResponse;
import com.example.classroom.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    Student toStudent(StudentCreationRequest request);

    StudentCreationResponse toResponse(Student student);
}
