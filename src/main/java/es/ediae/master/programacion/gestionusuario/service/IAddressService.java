package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.model.AddressModel;

public interface IAddressService {

    List<AddressModel> obtenerDirecciones(Integer userId, String nickUsuario, String nickContrasena);
    AddressModel obtenerDireccionPorId(Integer id, String nickUsuario, String nickContrasena);
    AddressModel crearDireccion(AddressModel addressModel, String nickUsuario, String nickContrasena);
    AddressModel actualizarDireccion(Integer id, AddressModel addressModel, String nickUsuario, String nickContrasena);
    Boolean eliminarDireccion(Integer id, String nickUsuario, String nickContrasena);
}
