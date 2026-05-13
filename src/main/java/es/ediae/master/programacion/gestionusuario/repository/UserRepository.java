package es.ediae.master.programacion.gestionusuario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ediae.master.programacion.gestionusuario.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // Ejercicio 2.2: validar nick unico al crear
    boolean existsByUsername(String username);
    
    // Ejercicio 2.2: validar nick unico al actualizar (excluir el propio id)
    boolean existsByUsernameAndIdNot(String username, Integer id);
    
    // Ejercicio 2.3: validar usuario de sesion
    boolean existsByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);
}
