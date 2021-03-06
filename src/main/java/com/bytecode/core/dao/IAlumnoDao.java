package com.bytecode.core.dao;

import java.util.List;

import com.bytecode.core.models.entity.Alumno;
import com.bytecode.core.models.entity.Curso;
import com.bytecode.core.models.entity.Matricula;

public interface IAlumnoDao {

		public List<Alumno> getAlumnos();		
		public void save (Alumno alumno);		
		public Alumno buscarAlumno(Long id);
		public void eliminar(Long id);
		
		public List<Curso> findByNombre(String term);
		
		public void saveMatricula(Matricula matricula);
		public Curso findCursoById(Long id);
}
