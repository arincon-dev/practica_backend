package es.ediae.master.programacion.gestionusuario.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;
import es.ediae.master.programacion.gestionusuario.repository.AddressRepository;
import es.ediae.master.programacion.gestionusuario.service.IAddressService;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public AddressEntity getAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public AddressEntity createAddress(AddressEntity address) {
        return addressRepository.save(address);
    }

    @Override
    public AddressEntity updateAddress(Integer id, AddressEntity address) {
        if (addressRepository.existsById(id)) {
            return addressRepository.save(address);
        }
        return null;
    }

    @Override
    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }
}
