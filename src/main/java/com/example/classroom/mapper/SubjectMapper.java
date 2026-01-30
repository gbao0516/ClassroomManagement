package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.SubjectCreationRequest;
import com.example.classroom.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public interface SubjectMapper {
    public Subject toSubject(SubjectCreationRequest subjectDTO);
}
