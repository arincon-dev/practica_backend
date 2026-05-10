package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.mapper.JobTitleMapper;
import es.ediae.master.programacion.gestionusuario.model.JobTitleModel;
import es.ediae.master.programacion.gestionusuario.repository.JobTitleRepository;
import es.ediae.master.programacion.gestionusuario.repository.UserRepository;
import es.ediae.master.programacion.gestionusuario.service.IJobTitleService;

@Service
public class JobTitleServiceImpl implements IJobTitleService {

    private final UserRepository userRepository;
    private final JobTitleRepository jobTitleRepository;
    private final JobTitleMapper jobTitleMapper;

    public JobTitleServiceImpl(UserRepository userRepository, JobTitleRepository jobTitleRepository,
            JobTitleMapper jobTitleMapper) {
        this.userRepository = userRepository;
        this.jobTitleRepository = jobTitleRepository;
        this.jobTitleMapper = jobTitleMapper;
    }

    @Override
    public List<JobTitleModel> obtenerPuestosTrabajo(String nickUsuario, String nickContrasena) {
        boolean isAuthenticated = userRepository.existsByUsernameAndPassword(nickUsuario, nickContrasena);
        if (!isAuthenticated)
            return null;

        return jobTitleRepository.findAll().stream().map(jobTitleMapper::toModel).toList();
    }

}
