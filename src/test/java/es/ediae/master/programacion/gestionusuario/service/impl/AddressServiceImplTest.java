package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.exception.AuthenticationException;
import es.ediae.master.programacion.gestionusuario.exception.ForbiddenException;
import es.ediae.master.programacion.gestionusuario.exception.ResourceNotFoundException;
import es.ediae.master.programacion.gestionusuario.mapper.AddressMapper;
import es.ediae.master.programacion.gestionusuario.model.AddressModel;
import es.ediae.master.programacion.gestionusuario.repository.AddressRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private AddressRepository addressRepository;
    @Mock private AddressMapper addressMapper;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    void obtenerDirecciones_ownerAccess_returnsList() {
        UserEntity user = new UserEntity();
        user.setId(1);
        AddressEntity addressEntity = new AddressEntity();
        AddressModel addressModel = new AddressModel();
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        given(addressRepository.findByUserId(1)).willReturn(List.of(addressEntity));
        given(addressMapper.toModel(addressEntity)).willReturn(addressModel);
        List<AddressModel> result = addressService.obtenerDirecciones(1, "john", "pass");
        assertThat(result).containsExactly(addressModel);
    }

    @Test
    void obtenerDirecciones_nonOwnerAccess_throwsForbiddenException() {
        UserEntity user = new UserEntity();
        user.setId(1);
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        assertThatThrownBy(() -> addressService.obtenerDirecciones(2, "john", "pass"))
                .isInstanceOf(ForbiddenException.class);
    }

    @Test
    void obtenerDirecciones_invalidCredentials_throwsAuthenticationException() {
        given(userRepository.findByUsernameAndPassword("john", "wrong")).willReturn(Optional.empty());
        assertThatThrownBy(() -> addressService.obtenerDirecciones(1, "john", "wrong"))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    void obtenerDireccion_ownerAccess_returnsModel() {
        UserEntity user = new UserEntity();
        user.setId(1);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setUser(user);
        AddressModel addressModel = new AddressModel();
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        given(addressRepository.findById(10)).willReturn(Optional.of(addressEntity));
        given(addressMapper.toModel(addressEntity)).willReturn(addressModel);
        AddressModel result = addressService.obtenerDireccion(10, "john", "pass");
        assertThat(result).isEqualTo(addressModel);
    }

    @Test
    void obtenerDireccion_nonOwnerAccess_throwsForbiddenException() {
        UserEntity owner = new UserEntity();
        owner.setId(2);
        UserEntity requester = new UserEntity();
        requester.setId(1);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setUser(owner);
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(requester));
        given(addressRepository.findById(10)).willReturn(Optional.of(addressEntity));
        assertThatThrownBy(() -> addressService.obtenerDireccion(10, "john", "pass"))
                .isInstanceOf(ForbiddenException.class);
    }

    @Test
    void obtenerDireccion_nonExistingAddress_throwsResourceNotFoundException() {
        UserEntity user = new UserEntity();
        user.setId(1);
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        given(addressRepository.findById(99)).willReturn(Optional.empty());
        assertThatThrownBy(() -> addressService.obtenerDireccion(99, "john", "pass"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void actualizarDireccion_ownerAccess_savesAndReturnsModel() {
        UserEntity user = new UserEntity();
        user.setId(1);
        AddressEntity existing = new AddressEntity();
        existing.setId(10);
        existing.setUser(user);
        AddressModel addressModel = new AddressModel();
        addressModel.setStreetName("New Street");
        addressModel.setStreetNumber(99);
        addressModel.setMainAddress(true);
        AddressModel saved = new AddressModel();
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        given(addressRepository.findById(10)).willReturn(Optional.of(existing));
        given(addressRepository.findMainAddressesByUserId(1)).willReturn(List.of(existing));
        given(addressRepository.save(existing)).willReturn(existing);
        given(addressMapper.toModel(existing)).willReturn(saved);
        AddressModel result = addressService.actualizarDireccion(10, addressModel, "john", "pass");
        assertThat(result).isEqualTo(saved);
        verify(addressRepository).save(existing);
    }

    @Test
    void crearDireccion_newMainAddress_clearsPreviousMain() {
        UserEntity user = new UserEntity();
        user.setId(1);

        AddressEntity previousMain = new AddressEntity();
        previousMain.setId(7);
        previousMain.setMainAddress(true);

        AddressModel request = new AddressModel();
        request.setMainAddress(true);

        AddressEntity entityToSave = new AddressEntity();
        AddressEntity savedEntity = new AddressEntity();
        AddressModel savedModel = new AddressModel();

        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        given(addressRepository.findMainAddressesByUserId(1)).willReturn(List.of(previousMain));
        given(addressMapper.toEntity(request, user)).willReturn(entityToSave);
        given(addressRepository.save(entityToSave)).willReturn(savedEntity);
        given(addressMapper.toModel(savedEntity)).willReturn(savedModel);

        AddressModel result = addressService.crearDireccion(request, "john", "pass");

        assertThat(result).isEqualTo(savedModel);
        assertThat(previousMain.getMainAddress()).isFalse();
    }

    @Test
    void actualizarDireccion_setAsMain_clearsOtherMainAddress() {
        UserEntity user = new UserEntity();
        user.setId(1);

        AddressEntity existing = new AddressEntity();
        existing.setId(10);
        existing.setUser(user);
        existing.setMainAddress(false);

        AddressEntity otherMain = new AddressEntity();
        otherMain.setId(11);
        otherMain.setUser(user);
        otherMain.setMainAddress(true);

        AddressModel request = new AddressModel();
        request.setStreetName("Updated");
        request.setStreetNumber(50);
        request.setMainAddress(true);

        AddressModel response = new AddressModel();

        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        given(addressRepository.findById(10)).willReturn(Optional.of(existing));
        given(addressRepository.findMainAddressesByUserId(1)).willReturn(List.of(existing, otherMain));
        given(addressRepository.save(existing)).willReturn(existing);
        given(addressMapper.toModel(existing)).willReturn(response);

        AddressModel result = addressService.actualizarDireccion(10, request, "john", "pass");

        assertThat(result).isEqualTo(response);
        assertThat(otherMain.getMainAddress()).isFalse();
    }

    @Test
    void actualizarDireccion_nonOwnerAccess_throwsForbiddenException() {
        UserEntity owner = new UserEntity();
        owner.setId(2);
        UserEntity requester = new UserEntity();
        requester.setId(1);
        AddressEntity existing = new AddressEntity();
        existing.setUser(owner);
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(requester));
        given(addressRepository.findById(10)).willReturn(Optional.of(existing));
        assertThatThrownBy(() -> addressService.actualizarDireccion(10, new AddressModel(), "john", "pass"))
                .isInstanceOf(ForbiddenException.class);
    }

    @Test
    void eliminarDireccion_ownerAccess_deletesAndReturnsTrue() {
        UserEntity user = new UserEntity();
        user.setId(1);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setUser(user);
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(user));
        given(addressRepository.findById(10)).willReturn(Optional.of(addressEntity));
        Boolean result = addressService.eliminarDireccion(10, "john", "pass");
        assertThat(result).isTrue();
        verify(addressRepository).deleteById(10);
    }

    @Test
    void eliminarDireccion_nonOwnerAccess_throwsForbiddenException() {
        UserEntity owner = new UserEntity();
        owner.setId(2);
        UserEntity requester = new UserEntity();
        requester.setId(1);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setUser(owner);
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(requester));
        given(addressRepository.findById(10)).willReturn(Optional.of(addressEntity));
        assertThatThrownBy(() -> addressService.eliminarDireccion(10, "john", "pass"))
                .isInstanceOf(ForbiddenException.class);
    }

    @Test
    void eliminarDireccion_invalidCredentials_throwsAuthenticationException() {
        given(userRepository.findByUsernameAndPassword("john", "wrong")).willReturn(Optional.empty());
        assertThatThrownBy(() -> addressService.eliminarDireccion(10, "john", "wrong"))
                .isInstanceOf(AuthenticationException.class);
    }
}
