package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.entity.JobTitleEntity;
import es.ediae.master.programacion.gestionusuario.model.JobTitleModel;

@Component
public class JobTitleMapper {

    public JobTitleModel toModel(JobTitleEntity entity) {
        return new JobTitleModel(entity.getId(), entity.getName());
    }
}
