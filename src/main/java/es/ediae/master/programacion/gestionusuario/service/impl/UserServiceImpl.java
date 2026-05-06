package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.GenderEntity;
import es.ediae.master.programacion.gestionusuario.entity.JobTitleEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.mapper.UserMapper;
import es.ediae.master.programacion.gestionusuario.model.UserModel;
import es.ediae.master.programacion.gestionusuario.repository.GenderRepository;
import es.ediae.master.programacion.gestionusuario.repository.JobTitleRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;
import es.ediae.master.programacion.gestionusuario.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final JobTitleRepository jobTitleRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, GenderRepository genderRepository,
            JobTitleRepository jobTitleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.jobTitleRepository = jobTitleRepository;
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
            return null;

        return userRepository.findAll().stream().map(userMapper::toModel).toList();
    }

    @Override
    public UserModel obtenerUsuarioPorId(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            return null;
        boolean isAuthenticated = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .isPresent();
        if (!isAuthenticated)
            return null;

        return userRepository.findById(id).map(userMapper::toModel).orElse(null);
    }

    @Override
    public UserModel crearUsuario(UserModel userModel, String nickUsuario, String nickContrasena) {
        if (userModel == null)
            return null;

        boolean isAuthenticated = userRepository
                .findByUsernameAndPassword(nickUsuario, nickContrasena)
                .isPresent();
        if (!isAuthenticated)
            return null;

        if (userRepository.existsByUsername(userModel.getUsername()))
            return null;

        if (userModel.getGender() == null) {
            return null;
        }
        
        Integer genderId = userModel.getGender().getId();
        if (genderId == null) {
            return null;
        }

        GenderEntity gender = genderRepository.findById(genderId).orElse(null);
        if (gender == null) {
            return null;
        }

        JobTitleEntity jobTitle = null;
        if (userModel.getJobTitle() != null) {
            Integer jobTitleId = userModel.getJobTitle().getId();
            if (jobTitleId == null) {
                return null;
            }

            jobTitle = jobTitleRepository.findById(jobTitleId).orElse(null);
            if (jobTitle == null) {
                return null;
            }
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
    public UserModel actualizarUsuario(Integer id, UserModel userModel, String nickUsuario, String nickContrasena) {
        if (id == null || userModel == null)
            return null;
        boolean isAuthenticated = userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena);
        if (!isAuthenticated)
            return null;

        if (userRepository.existsByUsernameAndIdNot(userModel.getUsername(), id))
            return null;

        UserEntity existing = userRepository.findById(id).orElse(null);
        if (existing == null)
            return null;

        existing.setUsername(userModel.getUsername());
        existing.setPassword(userModel.getPassword());
        existing.setName(userModel.getName());
        existing.setFirstSurname(userModel.getFirstSurname());
        existing.setSecondSurname(userModel.getSecondSurname());
        existing.setBirthdate(userModel.getBirthdate());
        existing.setBreakfastTime(userModel.getBreakfastTime());

        if (userModel.getIsAdmin() != null) {
            existing.setIsAdmin(userModel.getIsAdmin());
        }

        if (userModel.getGender() == null)
            return null;

        Integer genderId = userModel.getGender().getId();
        if (genderId == null)
            return null;

        GenderEntity gender = genderRepository.findById(genderId).orElse(null);
        if (gender == null)
            return null;
        existing.setGender(gender);

        if (userModel.getJobTitle() != null) {
            Integer jobTitleId = userModel.getJobTitle().getId();
            if (jobTitleId == null)
                return null;

            JobTitleEntity jobTitle = jobTitleRepository.findById(jobTitleId).orElse(null);
            if (jobTitle == null)
                return null;
            existing.setJobTitle(jobTitle);
        } else {
            existing.setJobTitle(null);
        }

        return userMapper.toModel(userRepository.save(existing));
    }

    @Override
    public Boolean eliminarUsuario(Integer id, String nickUsuario, String nickContrasena) {
        if (id == null)
            return false;
        boolean isAuthenticated = userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena);
        if (!isAuthenticated)
            return false;

        UserEntity existing = userRepository.findById(id).orElse(null);
        if (existing == null)
            return false;

        userRepository.delete(existing);
        return true;
    }

}
