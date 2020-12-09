package com.bytecode.core.dao;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import com.bytecode.core.models.entity.Alumno;
import com.bytecode.core.models.entity.CategoriaCursos;


@Repository("categoriadao")
public interface CategoriaDao extends JpaRepository<CategoriaCursos, Integer>{

	
	//public abstract List<CategoriaCursos> getCategoriaCursos();
	    
}
