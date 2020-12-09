package com.bytecode.core.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bytecode.core.dao.ProfesorDao;
import com.bytecode.core.models.entity.Profesor;
import com.bytecode.core.service.ProfesorService;

@Service("profesorservice")
public class ProfesorServiceImpl implements ProfesorService{

	@Autowired
	@Qualifier("profesordao")
	private ProfesorDao profesordao;
	
	@Override
	public List<Profesor> getProfesors() {
		// TODO Auto-generated method stub
		return profesordao.findAll();
	}

}
