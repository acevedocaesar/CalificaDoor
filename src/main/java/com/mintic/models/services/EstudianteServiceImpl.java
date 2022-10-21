package com.mintic.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mintic.models.dao.IEstudianteDao;
import com.mintic.models.entities.Estudiante;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired
	private IEstudianteDao estudianteDao;
	
	@Override
	public List<Estudiante> findAll(){
		return (List<Estudiante>) estudianteDao.findAll();
}

	@Override
	public Estudiante findById(Long id) {
		return estudianteDao.findById(id).orElse(null);
	}

	@Override
	public Estudiante save(Estudiante estudiante) {
		return estudianteDao.save(estudiante);
	}

	@Override
	public void delete(Estudiante estudiante) {
		estudianteDao.delete(estudiante);
	}
	}
