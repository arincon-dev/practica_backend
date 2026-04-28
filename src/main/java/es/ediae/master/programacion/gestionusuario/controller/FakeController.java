// package es.ediae.master.programacion.gestionusuario.controller;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import es.ediae.master.programacion.gestionusuario.entity.FakeEntity;
// import es.ediae.master.programacion.gestionusuario.service.IFakeService;
// import es.ediae.master.programacion.gestionusuario.service.impl.FakeServiceImpl;

// import java.util.List;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


// @RestController
// @RequestMapping("/fake")
// public class FakeController {

//     private final IFakeService fakeService;

//     public FakeController(FakeServiceImpl fakeService) {
//         this.fakeService = fakeService;
//     }

//     @GetMapping("/entity")
//     public List<FakeEntity> getAllFakeEntity() {
//         List<FakeEntity> fakeEntities = this.fakeService.obtenerAllFakeEntity();
//         return fakeEntities;
//     }
    

// }
