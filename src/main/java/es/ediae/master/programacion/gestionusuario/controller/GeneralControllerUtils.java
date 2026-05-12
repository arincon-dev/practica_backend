package es.ediae.master.programacion.gestionusuario.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public class GeneralControllerUtils {
    public static ResponseEntity<Map<String, Object>> crearRespuestaOk(Object data) {
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", data));
    }
}
