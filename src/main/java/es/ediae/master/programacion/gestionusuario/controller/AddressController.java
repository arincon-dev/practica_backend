package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;
import es.ediae.master.programacion.gestionusuario.service.impl.AddressService;


@RestController
@RequestMapping("/api/v1")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/user/{userId}/addresses")
    public List<AddressEntity> getAddressesByUser(@PathVariable Integer userId) {
        return addressService.findByUserId(userId);
    }

    @GetMapping("/addresses")
    public List<AddressEntity> getAllAddress() {
        return addressService.getAllAddresses();
    }
    
    @GetMapping("/address/{id}")
    public AddressEntity getAddressById(@PathVariable Integer id) {
        return addressService.getAddressById(id);
    }
}
