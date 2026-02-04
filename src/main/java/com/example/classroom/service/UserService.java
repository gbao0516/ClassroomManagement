package com.example.classroom.service;
import com.example.classroom.DTO.Response.UserResponse;
import com.example.classroom.exception.ResourceNotFoundException;
import com.example.classroom.model.User;
import com.example.classroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    public Page<UserResponse> getAllUsersPaginated(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::convertToResponse);
    }
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToResponse(user);
    }
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return convertToResponse(user);
    }
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        log.info("Deleting user: {}", user.getUsername());
        userRepository.delete(user);
    }
    @Transactional
    public UserResponse toggleUserStatus(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setEnabled(!user.isEnabled());
        user.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepository.save(user);
        log.info("User status toggled: {} - enabled: {}", user.getUsername(), user.isEnabled());
        return convertToResponse(updatedUser);
    }
    @Transactional
    public void unlockUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setLockedUntil(null);
        user.setFailedLoginAttempts(0);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("User unlocked: {}", user.getUsername());
    }
    @Transactional
    public void resetFailedLoginAttempts(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setFailedLoginAttempts(0);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("Failed login attempts reset for user: {}", user.getUsername());
    }
    private UserResponse convertToResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .teacherId(user.getTeacherId())
                .studentId(user.getStudentId())
                .build();
    }
}