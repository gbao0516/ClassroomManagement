package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.ClassroomCreationRequest;
import com.example.classroom.DTO.Response.ClassroomCreationResponse;
import com.example.classroom.model.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassroomMapper {
    Classroom toClassroom(ClassroomCreationRequest request);

    ClassroomCreationResponse toResponse(Classroom classroom);
}
