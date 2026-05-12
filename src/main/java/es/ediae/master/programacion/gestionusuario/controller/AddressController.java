package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ediae.master.programacion.gestionusuario.dto.AddressRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.AddressResponseDTO;
import es.ediae.master.programacion.gestionusuario.mapper.AddressMapper;
import es.ediae.master.programacion.gestionusuario.model.AddressModel;
import es.ediae.master.programacion.gestionusuario.service.IAddressService;


@RestController
@RequestMapping("/api/v1/direcciones")
public class AddressController {
    
    @Autowired
    private IAddressService addressService;
    @Autowired
    private AddressMapper addressMapper;

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<Map<String, Object>> obtenerDirecciones(@PathVariable("userId") Integer userId, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
            List<AddressModel> result = addressService.obtenerDirecciones(userId, nickUsuario, nickContrasena);
            List<AddressResponseDTO> response = result != null ? result.stream().map(addressMapper::toResponse).toList() : null;
            return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerDireccion(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        AddressModel result = addressService.obtenerDireccion(id, nickUsuario, nickContrasena);
        AddressResponseDTO response = result != null ? addressMapper.toResponse(result) : null;
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> crearDireccion(@RequestBody AddressRequestDTO addressRequest, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
            AddressModel model = addressMapper.toModel(addressRequest);
            AddressModel result = addressService.crearDireccion(model, nickUsuario, nickContrasena);
            AddressResponseDTO response = result != null ? addressMapper.toResponse(result) : null;
            return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarDireccion(@PathVariable("id") Integer id, @RequestBody AddressRequestDTO addressRequest, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
            AddressModel model = addressMapper.toModel(addressRequest);
            AddressModel result = addressService.actualizarDireccion(id, model, nickUsuario, nickContrasena);
            AddressResponseDTO response = result != null ? addressMapper.toResponse(result) : null;
            return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarDireccion(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
            Boolean result = addressService.eliminarDireccion(id, nickUsuario, nickContrasena);
            return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", result));
    }
}
