package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.ediae.master.programacion.gestionusuario.entity.GenderEntity;
import es.ediae.master.programacion.gestionusuario.entity.JobTitleEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.exception.AuthenticationException;
import es.ediae.master.programacion.gestionusuario.exception.DuplicateUsernameException;
import es.ediae.master.programacion.gestionusuario.exception.ResourceNotFoundException;
import es.ediae.master.programacion.gestionusuario.mapper.UserMapper;
import es.ediae.master.programacion.gestionusuario.model.UserModel;
import es.ediae.master.programacion.gestionusuario.repository.AddressRepository;
import es.ediae.master.programacion.gestionusuario.repository.GenderRepository;
import es.ediae.master.programacion.gestionusuario.repository.JobTitleRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;
import es.ediae.master.programacion.gestionusuario.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final JobTitleRepository jobTitleRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, GenderRepository genderRepository,
            JobTitleRepository jobTitleRepository, AddressRepository addressRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.addressRepository = addressRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Boolean iniciarSesion(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public List<UserModel> obtenerUsuarios(String nickUsuario, String nickContrasena) {
        boolean isAuthenticated = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .isPresent();
        if (!isAuthenticated)
            throw new AuthenticationException();

        return userRepository.findAll().stream().map(userMapper::toModel).toList();
    }

    @Override
    public UserModel obtenerUsuarioPorId(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            throw new ResourceNotFoundException("Usuario", id);

        if (!userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena))
            throw new AuthenticationException();

        return userRepository.findById(id)
                .map(userMapper::toModel)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
    }

    @Override
    @Transactional
    public UserModel crearUsuario(UserModel userModel, String nickUsuario, String nickContrasena) {
        if (userModel == null)
            throw new ResourceNotFoundException("Usuario", null);

        if (!userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena))
            throw new AuthenticationException();

        if (userRepository.existsByUsername(userModel.getUsername()))
            throw new DuplicateUsernameException(userModel.getUsername());

        if (userModel.getGender() == null || userModel.getGender().getId() == null) 
            throw new ResourceNotFoundException("Genero", null);

        Integer genderId = Objects.requireNonNull(userModel.getGender().getId());

        GenderEntity gender = genderRepository.findById(genderId)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", genderId));

        JobTitleEntity jobTitle = null;
        if (userModel.getJobTitle() != null) {
            Integer jobTitleId = userModel.getJobTitle().getId();
            if (jobTitleId == null)
                throw new ResourceNotFoundException("Puesto de trabajo", null);

            jobTitle = jobTitleRepository.findById(jobTitleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Puesto de trabajo", jobTitleId));
        }
        UserEntity entity = userMapper.toEntity(userModel);
        entity.setGender(gender);
        entity.setJobTitle(jobTitle);
        entity.setCreatedAt(new Date());

        if (entity.getIsAdmin() == null) {
            entity.setIsAdmin(false);
        }

        return userMapper.toModel(userRepository.save(entity));
    }

    @Override
    @Transactional
    public UserModel actualizarUsuario(Integer id, UserModel userModel, String nickUsuario, String nickContrasena) {
        if (id == null || userModel == null)
            throw new ResourceNotFoundException("Usuario", id);

        if (!userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena))
            throw new AuthenticationException();

        if (userRepository.existsByUsernameAndIdNot(userModel.getUsername(), id))
            throw new DuplicateUsernameException(userModel.getUsername());

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));

        existing.setUsername(userModel.getUsername());
        existing.setPassword(userModel.getPassword());
        existing.setName(userModel.getName());
        existing.setFirstSurname(userModel.getFirstSurname());
        existing.setSecondSurname(userModel.getSecondSurname());
        existing.setBirthDate(userModel.getBirthDate());
        existing.setBreakfastTime(userModel.getBreakfastTime());

        if (userModel.getIsAdmin() != null) {
            existing.setIsAdmin(userModel.getIsAdmin());
        }

        if (userModel.getGender() == null)
            throw new ResourceNotFoundException("Genero", null);

        Integer genderId = userModel.getGender().getId();
        if (genderId == null)
            throw new ResourceNotFoundException("Genero", null);

        GenderEntity gender = genderRepository.findById(genderId)
                .orElseThrow(() -> new ResourceNotFoundException("Genero", genderId));
        existing.setGender(gender);

        if (userModel.getJobTitle() != null) {
            Integer jobTitleId = userModel.getJobTitle().getId();
            if (jobTitleId == null)
                throw new ResourceNotFoundException("Puesto de trabajo", null);

            JobTitleEntity jobTitle = jobTitleRepository.findById(jobTitleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Puesto de trabajo", jobTitleId));
            existing.setJobTitle(jobTitle);
        } else {
            existing.setJobTitle(null);
        }

        return userMapper.toModel(userRepository.save(existing));
    }

    @Override
    @Transactional
    public Boolean eliminarUsuario(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            throw new ResourceNotFoundException("Usuario", id);

        if (!userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena))
            throw new AuthenticationException();

        UserEntity existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        existing = Objects.requireNonNull(existing);

        // cascade delete (so addresses dont get left alone) :)
        addressRepository.deleteByUserId(id);
        userRepository.delete(existing);
        return true;
    }

}
