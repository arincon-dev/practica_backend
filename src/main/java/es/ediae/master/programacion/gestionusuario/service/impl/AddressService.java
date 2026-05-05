package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.model.AddressModel;
import es.ediae.master.programacion.gestionusuario.repository.AddressRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;
import es.ediae.master.programacion.gestionusuario.service.IAddressService;

@Service
public class AddressService implements IAddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public AddressService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public boolean authenticate(String nickUsuario, String nickContrasena) {
        return userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena);
    }

    @Override
    public List<AddressModel> obtenerDirecciones(Integer userId, String nickUsuario, String nickContrasena) {
        if (!authenticate(nickUsuario, nickContrasena)) return null;
        return addressRepository.findByUserId(userId).stream().map(this::toModel).toList();
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

    private AddressModel toModel(AddressEntity e) {
    return new AddressModel(e.getId(), e.getStreetName(), e.getStreetNumber(),
        e.getMainAddress(), e.getUser().getId());
    }

    private AddressEntity toEntity(AddressModel m, UserEntity user) {
        AddressEntity e = new AddressEntity();
        e.setStreetName(m.getStreetName());
        e.setStreetNumber(m.getStreetNumber());
        e.setMainAddress(m.getMainAddress());
        e.setUser(user);
        return e;
    }
}
