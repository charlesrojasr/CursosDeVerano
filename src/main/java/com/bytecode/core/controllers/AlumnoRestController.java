package com.bytecode.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytecode.core.dao.IAlumnoDao;
import com.bytecode.core.models.entity.Alumno;

@RestController
@RequestMapping("/api")
public class AlumnoRestController {
	
	@Autowired
	private IAlumnoDao alumnodao;
	
	@RequestMapping("/listar")
	public List<Alumno> listar(){
		return alumnodao.getAlumnos();
	}
}
