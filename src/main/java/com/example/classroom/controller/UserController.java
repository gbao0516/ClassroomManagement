package com.example.classroom.controller;
import com.example.classroom.DTO.Response.MessageResponse;
import com.example.classroom.DTO.Response.UserResponse;
import com.example.classroom.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    private final UserService userService;
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.debug("Fetching all users");
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/paginated")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserResponse>> getAllUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "userId") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        log.debug("Fetching paginated users - page: {}, size: {}", page, size);
        Sort sort = sortDirection.equalsIgnoreCase("DESC") 
                ? Sort.by(sortBy).descending() 
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<UserResponse> users = userService.getAllUsersPaginated(pageable);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        log.debug("Fetching user by id: {}", id);
        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/username/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        log.debug("Fetching user by username: {}", username);
        UserResponse user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("User deleted successfully")
                .success(true)
                .build());
    }
    @PatchMapping("/{id}/toggle-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> toggleUserStatus(@PathVariable Long id) {
        log.info("Toggling status for user id: {}", id);
        UserResponse user = userService.toggleUserStatus(id);
        return ResponseEntity.ok(user);
    }
    @PatchMapping("/{id}/unlock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> unlockUser(@PathVariable Long id) {
        log.info("Unlocking user with id: {}", id);
        userService.unlockUser(id);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("User unlocked successfully")
                .success(true)
                .build());
    }
    @PatchMapping("/{id}/reset-login-attempts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> resetFailedLoginAttempts(@PathVariable Long id) {
        log.info("Resetting failed login attempts for user id: {}", id);
        userService.resetFailedLoginAttempts(id);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("Failed login attempts reset successfully")
                .success(true)
                .build());
    }
}