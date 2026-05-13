package es.ediae.master.programacion.gestionusuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.ediae.master.programacion.gestionusuario.entity.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    @Query("SELECT a FROM AddressEntity a WHERE a.user.id = :userId")
    List<AddressEntity> findByUserId(Integer userId);

    @Query("SELECT a FROM AddressEntity a WHERE a.user.id = :userId AND a.mainAddress = true")
    List<AddressEntity> findMainAddressesByUserId(Integer userId);

    //jpa auto translates this (naisuu)
    void deleteByUserId(Integer userId);
}
