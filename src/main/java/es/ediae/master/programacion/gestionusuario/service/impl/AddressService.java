package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.mapper.AddressMapper;
import es.ediae.master.programacion.gestionusuario.model.AddressModel;
import es.ediae.master.programacion.gestionusuario.repository.AddressRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;
import es.ediae.master.programacion.gestionusuario.service.IAddressService;

@Service
public class AddressService implements IAddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(UserRepository userRepository, AddressRepository addressRepository, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    // Internal helper to avoid repeating credential checks across service methods.
    // Not part of the IAddressService contract — callers should not authenticate directly.
    private boolean authenticate(String nickUsuario, String nickContrasena) {
        return userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena);
    }

    @Override
    public List<AddressModel> obtenerDirecciones(Integer userId, String nickUsuario, String nickContrasena) {
        if (!authenticate(nickUsuario, nickContrasena)) return null;
        return addressRepository.findByUserId(userId).stream().map(addressMapper::toModel).toList();
    }

    @Override
    public AddressModel obtenerDireccionPorId(Integer id, String nickUsuario, String nickContrasena) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AddressModel crearDireccion(AddressModel addressModel, String nickUsuario, String nickContrasena) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AddressModel actualizarDireccion(Integer id, AddressModel addressModel, String nickUsuario, String nickContrasena) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Boolean eliminarDireccion(Integer id, String nickUsuario, String nickContrasena) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
