package es.ediae.master.programacion.gestionusuario.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;

@RestControllerAdvice //Spring scans for @RestControllerAdvice automatically
@Hidden //Hide from Swagger documentation
public class GlobalExceptionHandler {

    private Map<String, Object> errorBody(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("type", "ERROR");
        body.put("message", message);
        body.put("data", null);
        return body;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthentication(AuthenticationException ex) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(errorBody(ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(errorBody(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateUsernameException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(errorBody(ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Map<String, Object>> handleForbidden(ForbiddenException ex) {
        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(errorBody(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorBody(ex.getMessage()));
    }
}
