package es.ediae.master.programacion.gestionusuario.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("Autenticación fallida: credenciales inválidas.");
    }

}
