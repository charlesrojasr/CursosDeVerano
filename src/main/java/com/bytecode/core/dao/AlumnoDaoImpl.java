package com.bytecode.core.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bytecode.core.models.entity.Alumno;
import com.bytecode.core.models.entity.Curso;
import com.bytecode.core.models.entity.Matricula;


@Repository
public class AlumnoDaoImpl implements IAlumnoDao{
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private ICursoDao cursoDAO;
	
	@Autowired
	private IMatriculaDao matriculadao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Alumno> getAlumnos() {		
		return em.createQuery("from Alumno").getResultList();
	}

	@Override
	@Transactional
	public void save(Alumno alumno) {
		if(alumno.getId() != null && alumno.getId()>0) {
			em.merge(alumno);
		}
		else {
			em.persist(alumno);
		}
		//em.persist(alumno);
	}

	@Override
	public Alumno buscarAlumno(Long id) {		
		return em.find(Alumno.class, id);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {		
		Alumno alumno = buscarAlumno(id);
		em.remove(alumno);
	}

	@Override
	public List<Curso> findByNombre(String term) {
		return cursoDAO.findByNombre(term);
	}

	@Override
	public void saveMatricula(Matricula matricula) {
		matriculadao.save(matricula);
	}

	@Override
	public Curso findCursoById(Long id) {
		// TODO Auto-generated method stub
		return cursoDAO.findById(id).orElse(null);
	}
}
