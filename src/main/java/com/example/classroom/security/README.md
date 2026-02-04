# Security Configuration for Classroom Management System

## Overview
This security package implements JWT-based authentication for the Classroom Management System using Spring Security.

## Components

### 1. Models
- **User.java**: User entity implementing UserDetails for Spring Security
- **Role.java**: Enum for user roles (ADMIN, TEACHER, STUDENT)

### 2. Security Configuration
- **SecurityConfig.java**: Main security configuration
  - Configures JWT authentication
  - Defines authorization rules for different endpoints
  - Sets up CORS configuration
  - Configures password encoder (BCrypt)

- **JwtTokenProvider.java**: JWT token generation and validation
  - Generates access tokens (24 hours)
  - Generates refresh tokens (7 days)
  - Validates tokens
  - Extracts user information from tokens

- **JwtAuthenticationFilter.java**: Filter to intercept and validate JWT tokens
  - Extracts JWT from Authorization header
  - Validates token
  - Sets authentication in SecurityContext

- **JwtAuthenticationEntryPoint.java**: Handles authentication errors
  - Returns 401 Unauthorized for failed authentication

- **CustomUserDetailsService.java**: Loads user details from database
  - Used by Spring Security for authentication

### 3. DTOs

#### Request DTOs
- **LoginRequest**: Username and password for login
- **RegisterRequest**: User registration data
- **RefreshTokenRequest**: Refresh token for getting new access token

#### Response DTOs
- **AuthResponse**: Contains access token, refresh token, and user info
- **UserResponse**: User information without sensitive data
- **MessageResponse**: Generic message response

### 4. Services
- **AuthService.java**: Authentication business logic
  - User registration
  - User login
  - Token refresh
  - Get current user
  - Logout

- **UserService.java**: User management
  - Get all users
  - Get user by ID/username
  - Delete user
  - Toggle user status (enable/disable)

### 5. Controllers
- **AuthController.java**: Authentication endpoints
  - POST /api/auth/register - Register new user
  - POST /api/auth/login - Login user
  - POST /api/auth/refresh - Refresh access token
  - GET /api/auth/me - Get current user info
  - POST /api/auth/logout - Logout user

- **UserController.java**: User management endpoints (Admin only)
  - GET /api/users - Get all users
  - GET /api/users/{id} - Get user by ID
  - GET /api/users/username/{username} - Get user by username
  - DELETE /api/users/{id} - Delete user
  - PATCH /api/users/{id}/toggle-status - Enable/disable user

## API Endpoints

### Public Endpoints (No authentication required)
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login

### Protected Endpoints (JWT required)
- `POST /api/auth/refresh` - Refresh token
- `GET /api/auth/me` - Get current user
- `POST /api/auth/logout` - Logout

### Admin Only Endpoints
- `GET /api/users/**` - User management
- `DELETE /api/users/**` - User management
- `PATCH /api/users/**` - User management
- `/api/admin/**` - All admin endpoints

### Teacher Endpoints
- `/api/teacher/**` - Accessible by ADMIN and TEACHER roles

### Student Endpoints
- `/api/student/**` - Accessible by all authenticated users

## Configuration Properties

Add these to your `application.properties`:

```properties
# JWT Configuration
jwt.secret=YOUR_SECRET_KEY_HERE
jwt.expiration=86400000
jwt.refresh.expiration=604800000
```

## Usage Examples

### 1. Register a new user
```bash
POST /api/auth/register
Content-Type: application/json

{
  "username": "admin",
  "password": "password123",
  "email": "admin@example.com",
  "fullName": "Admin User",
  "role": "ADMIN"
}
```

### 2. Login
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "password123"
}
```

Response:
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "userId": 1,
  "username": "admin",
  "email": "admin@example.com",
  "fullName": "Admin User",
  "role": "ADMIN"
}
```

### 3. Access protected endpoint
```bash
GET /api/auth/me
Authorization: Bearer <access_token>
```

### 4. Refresh token
```bash
POST /api/auth/refresh
Content-Type: application/json

{
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## Database Schema

The User table will be created automatically with these fields:
- user_id (PK, AUTO_INCREMENT)
- username (UNIQUE, NOT NULL)
- password (NOT NULL)
- email (UNIQUE, NOT NULL)
- full_name
- role (ENUM: ADMIN, TEACHER, STUDENT)
- enabled (BOOLEAN, default: true)
- created_at
- updated_at
- teacher_id (FK to teachers table, optional)
- student_id (FK to students table, optional)

## Security Features

1. **Password Encryption**: Passwords are encrypted using BCrypt
2. **JWT Authentication**: Stateless authentication using JWT tokens
3. **Role-Based Access Control**: Different roles have different permissions
4. **Token Expiration**: Access tokens expire after 24 hours, refresh tokens after 7 days
5. **CORS Configuration**: Configured for frontend applications
6. **Exception Handling**: Global exception handler for security errors

## Testing

You can test the security endpoints using:
- Postman
- cURL
- Any REST client

Make sure to include the JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

## Notes

- Change the JWT secret key in production (use environment variable)
- The first user must be created via the register endpoint
- Admin users can manage all users through the UserController endpoints
- Users can be linked to Teacher or Student entities via teacherId/studentId fields

