package com.bytecode.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

//import com.bytecode.core.models.entity.CategoriaCursos;
import com.bytecode.core.models.entity.Curso;


public interface ICursoDao extends CrudRepository<Curso, Integer>{
	/*
	public List<Curso> getCursos();
	public void save(Curso curso);
	public Curso buscarCurso(Long id);
	public void eliminar(Long id);
	
	public List<CategoriaCursos> findByNombre(String term);
	*/
	@Query("SELECT p FROM Curso p WHERE p.nombre like %?1%")
	public List<Curso> findByNombre(String term);        
}
