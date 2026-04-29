package es.ediae.master.programacion.gestionusuario.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.ediae.master.programacion.gestionusuario.entity.JobTitleEntity;

@Repository
public interface JobTitleRepository {

    List<JobTitleEntity> findAllJobTitles();
}
