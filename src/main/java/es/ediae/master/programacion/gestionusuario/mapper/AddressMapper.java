package es.ediae.master.programacion.gestionusuario.mapper;

import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;
import es.ediae.master.programacion.gestionusuario.entity.UserEntity;
import es.ediae.master.programacion.gestionusuario.model.AddressModel;

@Component
public class AddressMapper {

    public AddressModel toModel(AddressEntity e) {
        return new AddressModel(e.getId(), e.getStreetName(), e.getStreetNumber(),
            e.getMainAddress(), e.getUser().getId());
    }

    @NonNull
    public AddressEntity toEntity(@NonNull AddressModel model, @NonNull UserEntity user) {

        Objects.requireNonNull(model, "AddressModel cannot be null");
        Objects.requireNonNull(user, "UserEntity cannot be null");
        
        AddressEntity entity = new AddressEntity();
        entity.setStreetName(model.getStreetName());
        entity.setStreetNumber(model.getStreetNumber());
        entity.setMainAddress(model.getMainAddress());
        entity.setUser(user);
        return entity;
    }
}
