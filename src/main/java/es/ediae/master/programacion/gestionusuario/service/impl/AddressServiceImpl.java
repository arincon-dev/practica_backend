package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.exception.AuthenticationException;
import es.ediae.master.programacion.gestionusuario.exception.ForbiddenException;
import es.ediae.master.programacion.gestionusuario.exception.ResourceNotFoundException;
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

    private void desmarcarDireccionesPrincipales(Integer userId, Integer excludeId) {
        List<AddressEntity> mainAddresses = addressRepository.findMainAddressesByUserId(userId);
        if (mainAddresses == null || mainAddresses.isEmpty()) {
            return;
        }

        List<AddressEntity> toUpdate = mainAddresses.stream()
                .filter(address -> excludeId == null || !address.getId().equals(excludeId))
                .toList();

        if (toUpdate.isEmpty()) {
            return;
        }

        toUpdate.forEach(address -> address.setMainAddress(false));
        addressRepository.saveAll(toUpdate);
    }

    @Override
    public List<AddressModel> obtenerDirecciones(Integer userId, String nickUsuario, String nickContrasena) {
        if (userId == null)
            throw new ResourceNotFoundException("Usuario", userId);

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElseThrow(() -> new AuthenticationException());

        if (!user.getId().equals(userId))
            throw new ForbiddenException();

        return addressRepository.findByUserId(userId).stream().map(addressMapper::toModel).toList();
    }

    @Override
    public AddressModel obtenerDireccion(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            throw new ResourceNotFoundException("Direccion", id);

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElseThrow(() -> new AuthenticationException());

        AddressEntity address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion", id));

        if (!address.getUser().getId().equals(user.getId()))
            throw new ForbiddenException();

        return addressMapper.toModel(address);
    }

    @Override
    @Transactional
    public AddressModel crearDireccion(AddressModel addressModel, String nickUsuario, String nickContrasena) {
        if (addressModel == null)
            throw new ResourceNotFoundException("Direccion", null);

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElseThrow(() -> new AuthenticationException());

        Objects.requireNonNull(user);
        if (Boolean.TRUE.equals(addressModel.getMainAddress())) {
            desmarcarDireccionesPrincipales(user.getId(), null);
        }

        AddressEntity entity = addressMapper.toEntity(addressModel, user);
        return addressMapper.toModel(addressRepository.save(entity));
    }

    @Override
    @Transactional
    public AddressModel actualizarDireccion(Integer id, AddressModel addressModel, String nickUsuario,
            String nickContrasena) {
        if (id == null)
            throw new ResourceNotFoundException("Direccion", id);

        if (addressModel == null)
            throw new ResourceNotFoundException("Direccion", null);

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElseThrow(() -> new AuthenticationException());

        AddressEntity existing = addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Direccion", id));
        
        if (!existing.getUser().getId().equals(user.getId()))
            throw new ForbiddenException();

        if (Boolean.TRUE.equals(addressModel.getMainAddress())) {
            desmarcarDireccionesPrincipales(user.getId(), existing.getId());
        }
        
        existing.setStreetName(addressModel.getStreetName());
        existing.setStreetNumber(addressModel.getStreetNumber());
        existing.setMainAddress(addressModel.getMainAddress());

        return addressMapper.toModel(addressRepository.save(existing));
    }

    @Override
    @Transactional
    public Boolean eliminarDireccion(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            throw new ResourceNotFoundException("Direccion", id);

        UserEntity user = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .orElseThrow(() -> new AuthenticationException());

        AddressEntity address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion", id));

        if (!address.getUser().getId().equals(user.getId()))
            throw new ForbiddenException();

        addressRepository.deleteById(id);
        return true;
    }
}