package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.model.AddressModel;

public interface IAddressService {
    
    List<AddressModel> obtenerDirecciones(Integer userId, String nickUsuario, String nickContrasena);
    AddressModel obtenerDireccion(Integer id, String nickUsuario, String nickContrasena);
    AddressModel crearDireccion(AddressModel model, String nickUsuario, String nickContrasena);
    AddressModel actualizarDireccion(Integer id, AddressModel model, String nickUsuario, String nickContrasena);
    Boolean eliminarDireccion(Integer id, String nickUsuario, String nickContrasena);
}
