package es.ediae.master.programacion.gestionusuario.exception;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String username) {
        super("El nombre de usuario '" + username + "' ya está en uso.");
    }

}
