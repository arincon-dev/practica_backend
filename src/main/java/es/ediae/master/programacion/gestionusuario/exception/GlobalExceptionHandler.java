package es.ediae.master.programacion.gestionusuario.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@RestControllerAdvice //Spring scans for @RestControllerAdvice automatically
@Hidden //Hide from Swagger documentation
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthentication(AuthenticationException ex) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(Map.of("type", "ERROR", "message", ex.getMessage(), "data", null));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(Map.of("type", "ERROR", "message", ex.getMessage(), "data", null));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateUsernameException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(Map.of("type", "ERROR", "message", ex.getMessage(), "data", null));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Map<String, Object>> handleForbidden(ForbiddenException ex) {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(Map.of("type", "ERROR", "message", ex.getMessage(), "data", null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Map.of("type", "ERROR", "message", ex.getMessage(), "data", null));
    }
}
