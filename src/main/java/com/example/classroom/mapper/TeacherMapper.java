package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.TeacherCreationRequest;
import com.example.classroom.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public interface TeacherMapper {
    public Teacher toTeacher(TeacherCreationRequest TeacherDTO);
}
