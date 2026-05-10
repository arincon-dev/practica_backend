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

import es.ediae.master.programacion.gestionusuario.model.AddressModel;
import es.ediae.master.programacion.gestionusuario.service.IAddressService;


@RestController
@RequestMapping("/api/v1/direcciones")
public class AddressController {
    
    @Autowired
    private IAddressService addressService;

    @GetMapping("/usuario/{userId}")
    public ModelMap obtenerDirecciones(@PathVariable("userId") Integer userId, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            List<AddressModel> result = addressService.obtenerDirecciones(userId, nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @GetMapping("/{id}")
    public ModelMap obtenerDireccion(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            AddressModel result = addressService.obtenerDireccion(id, nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @PostMapping("/")
    public ModelMap crearDireccion(@RequestBody AddressModel addressModel, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            AddressModel result = addressService.crearDireccion(addressModel, nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @PutMapping("/{id}")
    public ModelMap actualizarDireccion(@PathVariable("id") Integer id, @RequestBody AddressModel addressModel, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            AddressModel result = addressService.actualizarDireccion(id, addressModel, nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
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
