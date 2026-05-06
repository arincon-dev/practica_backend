package es.ediae.master.programacion.gestionusuario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class AddressController {
    /*
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
    }*/
}
