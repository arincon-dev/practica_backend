package es.ediae.master.programacion.gestionusuario.controller;

import jakarta.validation.constraints.NotEmpty;

public class ProductDTO {

    private Integer id;
    @NotEmpty(message = "Product name must not be empty")
    private String name;
    @NotEmpty(message = "Product description must not be empty")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
