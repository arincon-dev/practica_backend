package es.ediae.master.programacion.gestionusuario.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Acceso prohibido: no tienes permisos para realizar esta acción.");
    }

}
