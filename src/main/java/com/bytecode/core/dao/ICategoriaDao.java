package com.bytecode.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bytecode.core.models.entity.Alumno;
import com.bytecode.core.models.entity.CategoriaCursos;

public interface ICategoriaDao extends CrudRepository<CategoriaCursos, Integer>{

	@Query("SELECT p FROM CategoriaCursos p WHERE p.nombre like %?1%")
	public List<CategoriaCursos> findByNombre(String term);
	    
}
