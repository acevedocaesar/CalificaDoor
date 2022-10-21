package com.mintic.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mintic.models.entities.Estudiante;

public interface IEstudianteDao extends CrudRepository <Estudiante, Long>{

}
