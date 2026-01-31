package com.example.classroom.service;

import com.example.classroom.DTO.Request.SessionCreationRequest;
import com.example.classroom.DTO.Response.SessionResponse;
import com.example.classroom.model.Classroom;
import com.example.classroom.model.Session;
import com.example.classroom.repository.ClassroomRepository;
import com.example.classroom.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final ClassroomRepository classroomRepository;

    public List<SessionResponse> getAllSessions() {
        return sessionRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<SessionResponse> getSessionsByClass(Long classId) {
        return sessionRepository.findByClassroomClassId(classId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public SessionResponse createSession(SessionCreationRequest request) {
        Classroom classroom = classroomRepository.findById(request.getClassId())
                .orElseThrow(() -> new IllegalArgumentException("Classroom not found"));

        Session session = new Session();
        session.setClassroom(classroom);
        session.setSessionDate(request.getSessionDate());
        session.setStartTime(request.getStartTime());
        session.setEndTime(request.getEndTime());
        session.setTopic(request.getTopic());
        session.setNote(request.getNote());
        session.setCreatedAt(LocalDateTime.now());

        Session saved = sessionRepository.save(session);
        return convertToResponse(saved);
    }

    private SessionResponse convertToResponse(Session session) {
        SessionResponse res = new SessionResponse();
        res.setSessionId(session.getSessionId());
        res.setClassId(session.getClassroom().getClassId());
        res.setClassName(session.getClassroom().getClassCode());
        res.setSessionDate(session.getSessionDate());
        res.setStartTime(session.getStartTime());
        res.setEndTime(session.getEndTime());
        res.setTopic(session.getTopic());
        res.setNote(session.getNote());
        res.setCreatedAt(session.getCreatedAt());
        return res;
    }
}
