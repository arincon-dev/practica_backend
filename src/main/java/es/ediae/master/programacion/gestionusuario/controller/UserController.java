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
    public ResponseEntity<Map<String, Object>> iniciarSesion(@RequestParam String username, @RequestParam String password) {
        boolean result = userService.iniciarSesion(username, password);
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", result));
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> obtenerUsuarios(@RequestParam String nickUsuario, @RequestParam String nickContrasena) {
            List<UserModel> result = userService.obtenerUsuarios(nickUsuario, nickContrasena);
            List<UserResponseDTO> response = result.stream()
                    .map(userMapper::toResponse)
                    .toList();
            return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerUsuario(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        UserModel result = userService.obtenerUsuarioPorId(id, nickUsuario, nickContrasena);
        UserResponseDTO response = result != null ? userMapper.toResponse(result) : null;
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }
    
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> crearUsuario(@RequestBody UserRequestDTO userRequest, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        UserModel model = userMapper.toModel(userRequest);
        UserModel result = userService.crearUsuario(model, nickUsuario, nickContrasena);
        UserResponseDTO response = result != null ? userMapper.toResponse(result) : null;
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarUsuario(@PathVariable("id") Integer id, @RequestBody UserRequestDTO userRequest, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        UserModel model = userMapper.toModel(userRequest);
        UserModel result = userService.actualizarUsuario(id, model, nickUsuario, nickContrasena);
        UserResponseDTO response = result != null ? userMapper.toResponse(result) : null;
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminarUsuario(@PathVariable("id") Integer id, @RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        boolean result = userService.eliminarUsuario(id, nickUsuario, nickContrasena);
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", result));
    }

    // GenderController
    @GetMapping("/generos")
    public ResponseEntity<Map<String, Object>> obtenerGeneros(@RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        List<GenderModel> result = genderService.obtenerGeneros(nickUsuario, nickContrasena);
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", result));
    }

    // JobTitleController
    @GetMapping("/puestos-de-trabajo")
    public ResponseEntity<Map<String, Object>> obtenerPuestosTrabajo(@RequestParam String nickUsuario, @RequestParam String nickContrasena) {
        List<JobTitleModel> result = jobTitleService.obtenerPuestosTrabajo(nickUsuario, nickContrasena);
        return ResponseEntity.ok(Map.of("type", "OK", "message", "", "data", result));
    }
}
