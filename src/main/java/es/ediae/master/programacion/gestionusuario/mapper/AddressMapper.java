package es.ediae.master.programacion.gestionusuario.mapper;

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

    public AddressEntity toEntity(AddressModel m, UserEntity user) {
        AddressEntity e = new AddressEntity();
        e.setStreetName(m.getStreetName());
        e.setStreetNumber(m.getStreetNumber());
        e.setMainAddress(m.getMainAddress());
        e.setUser(user);
        return e;
    }
}
