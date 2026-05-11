package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.mapper.AddressMapper;
import es.ediae.master.programacion.gestionusuario.model.AddressModel;
import es.ediae.master.programacion.gestionusuario.repository.AddressRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;
import es.ediae.master.programacion.gestionusuario.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository,
            AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public List<AddressModel> obtenerDirecciones(Integer userId, String nickUsuario, String nickContrasena) {
        if (userId == null)
            return null;

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElse(null);
        if (user == null)
            return null;

        return addressRepository.findByUserId(userId).stream().map(addressMapper::toModel).toList();
    }

    @Override
    public AddressModel obtenerDireccion(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            return null;

        boolean isAuthenticated = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .isPresent();
        if (!isAuthenticated)
            return null;

        return addressRepository.findById(id).map(addressMapper::toModel).orElse(null);
    }

    @Override
    @Transactional
    public AddressModel crearDireccion(AddressModel addressModel, String nickUsuario, String nickContrasena) {
        if (addressModel == null)
            return null;

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElse(null);
        if (user == null)
            return null;

        AddressEntity entity = addressMapper.toEntity(addressModel, user);
        return addressMapper.toModel(addressRepository.save(entity));
    }

    @Override
    @Transactional
    public AddressModel actualizarDireccion(Integer id, AddressModel addressModel, String nickUsuario,
            String nickContrasena) {
        if (id == null || addressModel == null)
            return null;

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElse(null);
        if (user == null)
            return null;

        AddressEntity existing = addressRepository.findById(id).orElse(null);
        if (existing == null)
            return null;
        
        if (!existing.getUser().getId().equals(user.getId()))
            return null;
        
        existing.setStreetName(addressModel.getStreetName());
        existing.setStreetNumber(addressModel.getStreetNumber());
        existing.setMainAddress(addressModel.getMainAddress());

        return addressMapper.toModel(addressRepository.save(existing));
    }

    @Override
    @Transactional
    public Boolean eliminarDireccion(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            return false;

        boolean isAuthenticated = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .isPresent();
        if (!isAuthenticated)
            return false;

        AddressEntity existing = addressRepository.findById(id).orElse(null);
        if (existing == null)
            return false;

        addressRepository.delete(existing);
        return true;
    }
}