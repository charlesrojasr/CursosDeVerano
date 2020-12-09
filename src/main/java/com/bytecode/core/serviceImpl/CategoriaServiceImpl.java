package com.bytecode.core.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bytecode.core.dao.CategoriaDao;
import com.bytecode.core.models.entity.CategoriaCursos;
import com.bytecode.core.service.CategoriaService;

@Service("categoriaservice")
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	@Qualifier("categoriadao")
	private CategoriaDao categoriadao;
	
	@Override
	public List<CategoriaCursos> getCategoriaCursos() {
		// TODO Auto-generated method stub
		return categoriadao.findAll();
	}

}
