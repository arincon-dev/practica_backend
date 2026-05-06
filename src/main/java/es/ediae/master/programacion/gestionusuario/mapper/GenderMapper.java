package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.entity.GenderEntity;
import es.ediae.master.programacion.gestionusuario.model.GenderModel;

@Component
public class GenderMapper {

    public GenderModel toModel(GenderEntity entity) {
        return new GenderModel(entity.getId(), entity.getName());
    }
}
