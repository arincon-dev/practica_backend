package es.ediae.master.programacion.gestionusuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1")
public class ValidationController {

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        //logic to create a product
        return ResponseEntity.ok(productDTO);
    }
}
