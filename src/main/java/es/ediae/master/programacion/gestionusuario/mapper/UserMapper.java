package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.model.GenderModel;
import es.ediae.master.programacion.gestionusuario.model.JobTitleModel;
import es.ediae.master.programacion.gestionusuario.model.UserModel;

@Component
public class UserMapper {

    public UserModel toModel(UserEntity entity) {

        GenderModel genderModel = entity.getGender() != null 
            ? new GenderModel(entity.getGender().getId(), entity.getGender().getName()) : null;

        JobTitleModel jobTitleModel = entity.getJobTitle() != null 
            ? new JobTitleModel(entity.getJobTitle().getId(), entity.getJobTitle().getName()) : null;

        return new UserModel(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getCreatedAt(),
            entity.getName(), entity.getFirstSurname(), entity.getSecondSurname(), entity.getBirthdate(),
            entity.getBreakfastTime(), entity.getIsAdmin(), genderModel, jobTitleModel);
    }

    public UserEntity toEntity(UserModel model) {

        UserEntity entity = new UserEntity();
        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setName(model.getName());
        entity.setFirstSurname(model.getFirstSurname());
        entity.setSecondSurname(model.getSecondSurname());
        entity.setBirthdate(model.getBirthdate());
        entity.setBreakfastTime(model.getBreakfastTime());
        entity.setIsAdmin(model.getIsAdmin());
        return entity;
    }
}
