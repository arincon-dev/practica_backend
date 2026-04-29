package es.ediae.master.programacion.gestionusuario.service;

import java.util.List;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;

public interface IAddressService {

    public List<AddressEntity> getAllAddresses();
    public AddressEntity getAddressById(Integer id);
    public AddressEntity createAddress(AddressEntity address);
    public AddressEntity updateAddress(Integer id, AddressEntity address);
    public void deleteAddress(Integer id);
}
