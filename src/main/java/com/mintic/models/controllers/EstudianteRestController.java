package com.mintic.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.models.entities.Estudiante;
import com.mintic.models.services.IEstudianteService;

@RestController
@RequestMapping("/api")
public class EstudianteRestController {
	
	@Autowired
	private IEstudianteService estudianteService;
	
	@GetMapping("/estudiantes")
	public List<Estudiante> index(){
		return estudianteService.findAll();
	
	}
		
	@GetMapping("/estudiante/{id}")
	public Estudiante show(@PathVariable Long id)	{
		return estudianteService.findById(id);
	}
	
	@PostMapping("/estudiantes")
	public ResponseEntity<?> create(@Valid @RequestBody Estudiante estudiante, BindingResult result){
		
		Estudiante estudianteNew=null;
		
		Map<String,Object> response=new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors=result.getFieldErrors()
										.stream()
										.map(err-> "El campo "+ err.getField()+ " "+
									     err.getDefaultMessage()).collect(Collectors.toList());
			
			response.put("errors",errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);			
			
		}
		try {
			estudianteNew=this.estudianteService.save(estudiante);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje","El estudiante ha sido creado con exito!");
		response.put("estudiante",estudianteNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);		
	}

	@PutMapping("/estudiante/{id}")
	public ResponseEntity<?> update(@Valid  @RequestBody Estudiante estudiante, BindingResult result,@PathVariable Long id){
		
		Estudiante estudianteActual=this.estudianteService.findById(id);
		
		Estudiante estudianteActulizado=null;
		
		Map<String, Object> response=new HashMap<>();
		
		if(result.hasErrors()){
			
			List<String> errors=result.getFieldErrors()
					            .stream()
					            .map(err-> "El campo "+ err.getField()+ " "+
					            err.getDefaultMessage()).collect(Collectors.toList());
			
			
			response.put("errors",errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);			
			
		}
		
		if(estudianteActual==null) {
			response.put("mensaje","Error: no se puede editar el cliente Id:"+id+" no existe en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}	
		
		try {
			estudianteActual.setDocumento(estudiante.getDocumento());
			estudianteActual.setNombres(estudiante.getNombres());
			estudianteActual.setApellidos(estudiante.getApellidos());
			estudianteActual.setEmail(estudiante.getEmail());
			estudianteActulizado=this.estudianteService.save(estudianteActual);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje","El estudiante ha sido actulizado con exito!");
		response.put("estudiante",estudianteActulizado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/estudiante/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Map<String,Object> response=new HashMap<>();
		
		try{
			Estudiante estudiante=this.estudianteService.findById(id);
			this.estudianteService.delete(estudiante);
			
		}catch(DataAccessException e){
			response.put("mensaje", "Error al elminar el estudiante en la base de datos");
			response.put("erro",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
		}
	    response.put("mensaje", "El estudiante ha sido eliminado con exito!");
	    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
		
	}

