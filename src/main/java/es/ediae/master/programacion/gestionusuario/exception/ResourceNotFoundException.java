package es.ediae.master.programacion.gestionusuario.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Integer id) {
        super(resourceName + " con ID " + id + " no encontrado.");
    }

}
