package com.bytecode.core.service;

import java.util.List;

import com.bytecode.core.models.entity.Curso;

public interface CursoService {

	public List<Curso> getCursos();
	public void save(Curso curso);
	public Curso buscarCurso(Long id);
	public void eliminar(Long id);
}
