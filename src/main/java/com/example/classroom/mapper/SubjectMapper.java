package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.SubjectCreationRequest;
import com.example.classroom.DTO.Response.SubjectCreationResponse;
import com.example.classroom.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectMapper {
    Subject toSubject(SubjectCreationRequest request);

    SubjectCreationResponse toResponse(Subject subject);
}
