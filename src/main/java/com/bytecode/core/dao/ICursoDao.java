package com.bytecode.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bytecode.core.models.entity.CategoriaCursos;
import com.bytecode.core.models.entity.Curso;


public interface ICursoDao {

	public List<Curso> getCursos();
	public void save(Curso curso);
	public Curso buscarCurso(Long id);
	public void eliminar(Long id);
	
	public List<CategoriaCursos> findByNombre(String term);
}
