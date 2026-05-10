package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.model.JobTitleModel;

public interface IJobTitleService {

    List<JobTitleModel> obtenerPuestosTrabajo(String nickUsuario, String nickContrasena);
}
