package com.bytecode.core.dao;

import org.springframework.data.repository.CrudRepository;

import com.bytecode.core.models.entity.Usuario;

public interface IUsuarioDao  extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
