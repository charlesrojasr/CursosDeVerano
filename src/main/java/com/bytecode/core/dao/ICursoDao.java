package com.bytecode.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bytecode.core.models.entity.Curso;


public interface ICursoDao {

	public List<Curso> getCursos();
	public void save(Curso curso);
	public Curso buscarCurso(Long id);
	public void eliminar(Long id);
	
	@Query("SELECT p FROM Curso p WHERE p.nombre like %?1%")
	public List<Curso> findByNombre(String term);
}
