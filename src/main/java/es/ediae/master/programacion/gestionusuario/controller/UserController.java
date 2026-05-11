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

import es.ediae.master.programacion.gestionusuario.dto.UserRequestDTO;
import es.ediae.master.programacion.gestionusuario.dto.UserResponseDTO;
import es.ediae.master.programacion.gestionusuario.mapper.UserMapper;
import es.ediae.master.programacion.gestionusuario.model.GenderModel;
import es.ediae.master.programacion.gestionusuario.model.JobTitleModel;
import es.ediae.master.programacion.gestionusuario.model.UserModel;
import es.ediae.master.programacion.gestionusuario.service.IGenderService;
import es.ediae.master.programacion.gestionusuario.service.IJobTitleService;
import es.ediae.master.programacion.gestionusuario.service.IUserService;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IGenderService genderService;
    @Autowired
    private IJobTitleService jobTitleService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/iniciar-sesion")
    public ModelMap iniciarSesion(@RequestParam String username, @RequestParam String password) {
        try {
            boolean result = userService.iniciarSesion(username, password);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }
    @GetMapping("/")
    public ModelMap obtenerUsuarios(@RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            List<UserModel> result = userService.obtenerUsuarios(nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }
    
    @GetMapping("/{id}")
    public ModelMap obtenerUsuario(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            UserModel result = userService.obtenerUsuarioPorId(id, nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }
    
    @PostMapping("/")
    public ModelMap crearUsuario(@RequestBody UserRequestDTO userRequest, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            UserModel model = userMapper.toModel(userRequest);
            UserModel result = userService.crearUsuario(model, nickUsuario, nickContrasena);
            UserResponseDTO response = result != null ? userMapper.toResponse(result) : null;
            return GeneralControllerUtils.crearRespuestaModelMapOk(response);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @PutMapping("/{id}")
    public ModelMap actualizarUsuario(@PathVariable("id") Integer id, @RequestBody UserModel userModel, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            UserModel result = userService.actualizarUsuario(id, userModel, nickUsuario, nickContrasena);
            UserResponseDTO response = result != null ? userMapper.toResponse(result) : null;
            return GeneralControllerUtils.crearRespuestaModelMapOk(response);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ModelMap eliminarUsuario(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            boolean result = userService.eliminarUsuario(id, nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    // GenderController
    @GetMapping("/generos")
    public ModelMap obtenerGeneros(@RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            List<GenderModel> result = genderService.obtenerGeneros(nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }

    // JobTitleController
    @GetMapping("/puestos-de-trabajo")
    public ModelMap obtenerPuestosTrabajo(@RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        try {
            List<JobTitleModel> result = jobTitleService.obtenerPuestosTrabajo(nickUsuario, nickContrasena);
            return GeneralControllerUtils.crearRespuestaModelMapOk(result);
        } catch (Exception e) {
            return GeneralControllerUtils.crearRespuestaModelMapError(e);
        }
    }
}
