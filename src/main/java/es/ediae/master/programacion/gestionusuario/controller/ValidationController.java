package es.ediae.master.programacion.gestionusuario.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1")
public class ValidationController {
    /*
    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        //logic to create a product
        return ResponseEntity.ok(productDTO);
    }
    */
}
