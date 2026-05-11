package es.ediae.master.programacion.gestionusuario.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import es.ediae.master.programacion.gestionusuario.constant.GeneralConstant;

import org.springframework.ui.ModelMap;

public class GeneralControllerUtils {
    public static ModelMap crearRespuestaModelMapOk(Object data) {
        ModelMap response = new ModelMap();
        response.put(GeneralConstant.TYPE, GeneralConstant.OK);
        response.put(GeneralConstant.EXCEPTION, null);
        response.put(GeneralConstant.DATA, data);
        return response;
    }

    public static ModelMap crearRespuestaModelMapError(Exception ex) {
        ModelMap response = new ModelMap();
        response.put(GeneralConstant.TYPE, GeneralConstant.EXCEPTION);
        response.put(GeneralConstant.EXCEPTION, ex);
        response.put(GeneralConstant.DATA, null);
        return response;
    }

    public static ResponseEntity<Map<String, Object>> crearRespuestaOk(Object data) {
    return ResponseEntity.ok(Map.of("type", "OK", "exception", "", "data", data));
}
}
