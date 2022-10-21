package com.mintic.models.services;

import java.util.List;

import com.mintic.models.entities.Estudiante;

public interface IEstudianteService {
	
	public List<Estudiante> findAll();
	public Estudiante findById(Long id);
	public Estudiante save(Estudiante estudiante);
	public void delete (Estudiante estudiante);
	

}
