package es.ediae.master.programacion.gestionusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
    public ModelMap obtenerDirecciones(@PathVariable("userId") Integer userId, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            List<AddressModel> result = addressService.obtenerDirecciones(userId, nickUsuario, nickContrasena);
            List<AddressResponseDTO> response = result != null ? result.stream().map(addressMapper::toResponse).toList() : null;
            return GeneralControllerUtils.crearRespuestaModelMapOk(response);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @GetMapping("/{id}")
    public ModelMap obtenerDireccion(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            AddressModel result = addressService.obtenerDireccion(id, nickUsuario, nickContrasena);
            AddressResponseDTO response = result != null ? addressMapper.toResponse(result) : null;
            return GeneralControllerUtils.crearRespuestaModelMapOk(response);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @PostMapping("/")
    public ModelMap crearDireccion(@RequestBody AddressRequestDTO addressRequest, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            AddressModel model = addressMapper.toModel(addressRequest);
            AddressModel result = addressService.crearDireccion(model, nickUsuario, nickContrasena);
            AddressResponseDTO response = result != null ? addressMapper.toResponse(result) : null;
            return GeneralControllerUtils.crearRespuestaModelMapOk(response);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @PutMapping("/{id}")
    public ModelMap actualizarDireccion(@PathVariable("id") Integer id, @RequestBody AddressRequestDTO addressRequest, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            AddressModel model = addressMapper.toModel(addressRequest);
            AddressModel result = addressService.actualizarDireccion(id, model, nickUsuario, nickContrasena);
            AddressResponseDTO response = result != null ? addressMapper.toResponse(result) : null;
            return GeneralControllerUtils.crearRespuestaModelMapOk(response);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ModelMap eliminarDireccion(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            Boolean result = addressService.eliminarDireccion(id, nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }
}
