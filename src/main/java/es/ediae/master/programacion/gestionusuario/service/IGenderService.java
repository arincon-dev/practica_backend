package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.model.GenderModel;

public interface IGenderService {

    List<GenderModel> obtenerGeneros(String nickUsuario, String nickContrasena);
}
