package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.exception.AuthenticationException;
import es.ediae.master.programacion.gestionusuario.mapper.GenderMapper;
import es.ediae.master.programacion.gestionusuario.model.GenderModel;
import es.ediae.master.programacion.gestionusuario.repository.GenderRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;
import es.ediae.master.programacion.gestionusuario.service.IGenderService;

@Service
public class GenderServiceImpl implements IGenderService {

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;

    public GenderServiceImpl(UserRepository userRepository, GenderRepository genderRepository,
            GenderMapper genderMapper) {
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;
        this.genderMapper = genderMapper;
    }

    @Override
    public List<GenderModel> obtenerGeneros(String nickUsuario, String nickContrasena) {
        if (!userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena))
            throw new AuthenticationException();

        return genderRepository.findAll().stream().map(genderMapper::toModel).toList();
    }


}
