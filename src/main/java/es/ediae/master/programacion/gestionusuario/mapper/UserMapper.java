package es.ediae.master.programacion.gestionusuario.mapper;

import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.dto.UserRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UserResponseDTO;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.model.GenderModel;
import es.ediae.master.programacion.gestionusuario.model.JobTitleModel;
import es.ediae.master.programacion.gestionusuario.model.UserModel;

@Component
public class UserMapper {

        public UserModel toModel(UserEntity entity) {

                GenderModel genderModel = entity.getGender() != null
                                ? new GenderModel(entity.getGender().getId(), entity.getGender().getName())
                                : null;

                JobTitleModel jobTitleModel = entity.getJobTitle() != null
                                ? new JobTitleModel(entity.getJobTitle().getId(), entity.getJobTitle().getName())
                                : null;

                return new UserModel(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getCreatedAt(),
                                entity.getName(), entity.getFirstSurname(), entity.getSecondSurname(),
                                entity.getBirthDate(),
                                entity.getBreakfastTime(), entity.getIsAdmin(), genderModel, jobTitleModel);
        }

        public UserEntity toEntity(UserModel model) {

                UserEntity entity = new UserEntity();
                entity.setUsername(model.getUsername());
                entity.setPassword(model.getPassword());
                entity.setName(model.getName());
                entity.setFirstSurname(model.getFirstSurname());
                entity.setSecondSurname(model.getSecondSurname());
                entity.setBirthDate(model.getBirthDate());
                entity.setBreakfastTime(model.getBreakfastTime());
                entity.setIsAdmin(model.getIsAdmin());
                return entity;
        }

        public UserModel toModel(UserRequestDTO request) {
                GenderModel gender = request.getGenderId() != null
                                ? new GenderModel(request.getGenderId(), null)
                                : null;
                JobTitleModel jobTitle = request.getJobTitleId() != null
                                ? new JobTitleModel(request.getJobTitleId(), null)
                                : null;

                return new UserModel(null, request.getUsername(), request.getPassword(), null,
                                request.getName(), request.getFirstSurname(), request.getSecondSurname(),
                                request.getBirthDate(), request.getBreakfastTime(), request.getIsAdmin(),
                                gender, jobTitle);

        }

        public UserResponseDTO toResponse(UserModel model) {
                UserResponseDTO response = new UserResponseDTO();
                response.setId(model.getId());
                response.setUsername(model.getUsername());
                response.setPassword(model.getPassword());
                response.setCreatedAt(model.getCreatedAt());
                response.setName(model.getName());
                response.setFirstSurname(model.getFirstSurname());
                response.setSecondSurname(model.getSecondSurname());
                response.setBirthDate(model.getBirthDate());
                response.setBreakfastTime(model.getBreakfastTime());
                response.setIsAdmin(model.getIsAdmin());
                response.setGender(model.getGender());
                response.setJobTitle(model.getJobTitle());
                return response;
        }
}
