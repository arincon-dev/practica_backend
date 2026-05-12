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

import es.ediae.master.programacion.gestionusuario.entity.GenderEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.exception.AuthenticationException;
import es.ediae.master.programacion.gestionusuario.exception.DuplicateUsernameException;
import es.ediae.master.programacion.gestionusuario.exception.ResourceNotFoundException;
import es.ediae.master.programacion.gestionusuario.mapper.UserMapper;
import es.ediae.master.programacion.gestionusuario.model.GenderModel;
import es.ediae.master.programacion.gestionusuario.model.UserModel;
import es.ediae.master.programacion.gestionusuario.repository.AddressRepository;
import es.ediae.master.programacion.gestionusuario.repository.GenderRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private GenderRepository genderRepository;
    @Mock private AddressRepository addressRepository;
    @Mock private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void iniciarSesion_validCredentials_returnsTrue() {
        given(userRepository.existsByUsernameAndPassword("john", "pass")).willReturn(true);
        Boolean result = userService.iniciarSesion("john", "pass");
        assertThat(result).isTrue();
    }

    @Test
    void iniciarSesion_invalidCredentials_returnsFalse() {
        given(userRepository.existsByUsernameAndPassword("john", "wrong")).willReturn(false);
        Boolean result = userService.iniciarSesion("john", "wrong");
        assertThat(result).isFalse();
    }

    @Test
    void obtenerUsuarios_validCredentials_returnsList() {
        UserEntity entity = new UserEntity();
        UserModel model = new UserModel();
        given(userRepository.findByUsernameAndPassword("john", "pass")).willReturn(Optional.of(entity));
        given(userRepository.findAll()).willReturn(List.of(entity));
        given(userMapper.toModel(entity)).willReturn(model);
        List<UserModel> result = userService.obtenerUsuarios("john", "pass");
        assertThat(result).containsExactly(model);
    }

    @Test
    void obtenerUsuarios_invalidCredentials_throwsAuthenticationException() {
        given(userRepository.findByUsernameAndPassword("john", "wrong")).willReturn(Optional.empty());
        assertThatThrownBy(() -> userService.obtenerUsuarios("john", "wrong"))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    void obtenerUsuarioPorId_existingUser_returnsModel() {
        UserEntity entity = new UserEntity();
        UserModel model = new UserModel();
        given(userRepository.existsByUsernameAndPassword("john", "pass")).willReturn(true);
        given(userRepository.findById(1)).willReturn(Optional.of(entity));
        given(userMapper.toModel(entity)).willReturn(model);
        UserModel result = userService.obtenerUsuarioPorId(1, "john", "pass");
        assertThat(result).isEqualTo(model);
    }

    @Test
    void obtenerUsuarioPorId_nonExistingUser_throwsResourceNotFoundException() {
        given(userRepository.existsByUsernameAndPassword("john", "pass")).willReturn(true);
        given(userRepository.findById(99)).willReturn(Optional.empty());
        assertThatThrownBy(() -> userService.obtenerUsuarioPorId(99, "john", "pass"))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void obtenerUsuarioPorId_invalidCredentials_throwsAuthenticationException() {
        given(userRepository.existsByUsernameAndPassword("john", "wrong")).willReturn(false);
        assertThatThrownBy(() -> userService.obtenerUsuarioPorId(1, "john", "wrong"))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    void crearUsuario_duplicateUsername_throwsDuplicateUsernameException() {
        GenderModel genderModel = new GenderModel();
        genderModel.setId(1);
        UserModel userModel = new UserModel();
        userModel.setUsername("john");
        userModel.setGender(genderModel);
        given(userRepository.existsByUsernameAndPassword("john", "pass")).willReturn(true);
        given(userRepository.existsByUsername("john")).willReturn(true);
        assertThatThrownBy(() -> userService.crearUsuario(userModel, "john", "pass"))
                .isInstanceOf(DuplicateUsernameException.class);
    }

    @Test
    void crearUsuario_invalidCredentials_throwsAuthenticationException() {
        UserModel userModel = new UserModel();
        userModel.setUsername("newuser");
        given(userRepository.existsByUsernameAndPassword("john", "wrong")).willReturn(false);
        assertThatThrownBy(() -> userService.crearUsuario(userModel, "john", "wrong"))
                .isInstanceOf(AuthenticationException.class);
    }

    @Test
    void crearUsuario_validRequest_savesAndReturnsModel() {
        GenderModel genderModel = new GenderModel();
        genderModel.setId(1);
        UserModel userModel = new UserModel();
        userModel.setUsername("newuser");
        userModel.setGender(genderModel);
        GenderEntity genderEntity = new GenderEntity();
        UserEntity entity = new UserEntity();
        UserModel saved = new UserModel();
        given(userRepository.existsByUsernameAndPassword("john", "pass")).willReturn(true);
        given(userRepository.existsByUsername("newuser")).willReturn(false);
        given(genderRepository.findById(1)).willReturn(Optional.of(genderEntity));
        given(userMapper.toEntity(userModel)).willReturn(entity);
        given(userRepository.save(entity)).willReturn(entity);
        given(userMapper.toModel(entity)).willReturn(saved);
        UserModel result = userService.crearUsuario(userModel, "john", "pass");
        assertThat(result).isEqualTo(saved);
        verify(userRepository).save(entity);
    }

    @Test
    void eliminarUsuario_validRequest_deletesAddressesAndUser() {
        UserEntity entity = new UserEntity();
        entity.setId(1);
        given(userRepository.existsByUsernameAndPassword("john", "pass")).willReturn(true);
        given(userRepository.findById(1)).willReturn(Optional.of(entity));
        Boolean result = userService.eliminarUsuario(1, "john", "pass");
        assertThat(result).isTrue();
        verify(addressRepository).deleteByUserId(1);
        verify(userRepository).delete(entity);
    }

    @Test
    void eliminarUsuario_nonExistingUser_throwsResourceNotFoundException() {
        given(userRepository.existsByUsernameAndPassword("john", "pass")).willReturn(true);
        given(userRepository.findById(99)).willReturn(Optional.empty());
        assertThatThrownBy(() -> userService.eliminarUsuario(99, "john", "pass"))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
