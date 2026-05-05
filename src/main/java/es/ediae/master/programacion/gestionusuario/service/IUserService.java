package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.model.UserModel;

public interface IUserService {

    Boolean iniciarSesion(String username, String password);
    List<UserModel> obtenerUsuarios(String nickUsuario, String nickContrasena);
    UserModel obtenerUsuarioPorId(Integer id, String nickUsuario, String nickContrasena);
    UserModel crearUsuario(UserModel userModel, String nickUsuario, String nickContrasena);
    UserModel actualizarUsuario(Integer id, UserModel userModel, String nickUsuario, String nickContrasena);
    Boolean eliminarUsuario(Integer id, String nickUsuario, String nickContrasena);
    
}
