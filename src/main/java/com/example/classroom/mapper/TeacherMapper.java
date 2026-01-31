package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.TeacherCreationRequest;
import com.example.classroom.DTO.Response.TeacherCreationResponse;
import com.example.classroom.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {
    Teacher toTeacher(TeacherCreationRequest request);

    TeacherCreationResponse toResponse(Teacher teacher);
}
