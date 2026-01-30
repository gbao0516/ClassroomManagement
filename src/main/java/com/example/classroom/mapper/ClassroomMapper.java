package com.example.classroom.mapper;

import com.example.classroom.DTO.Request.ClassroomCreationRequest;
import com.example.classroom.model.Classroom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")


public interface ClassroomMapper {
    public Classroom toClassroom(ClassroomCreationRequest classDTO);
}
