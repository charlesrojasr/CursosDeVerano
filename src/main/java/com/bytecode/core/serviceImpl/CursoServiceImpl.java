package com.bytecode.core.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.bytecode.core.models.entity.Curso;
import com.bytecode.core.service.CursoService;

@Service("cursoservice")
public class CursoServiceImpl implements CursoService{

	@PersistenceContext
	private EntityManager em;
	
	//@Autowired
	//private ICategoriaDao categoriadao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> getCursos() {
		return em.createQuery("from Curso").getResultList();
	}

	@Override
	@Transactional
	public void save(Curso curso) {
		if(curso.getId() != null && curso.getId()>0) {
			em.merge(curso);
		}
		else {
			em.persist(curso);
		}
		//em.persist(alumno);
	}

	@Override
	public Curso buscarCurso(Long id) {
		return em.find(Curso.class, id);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		Curso curso = buscarCurso(id);
		em.remove(curso);
	}

	/*
	@Override
	@Transactional
	public List<CategoriaCursos> findByNombre(String term) {
		// TODO Auto-generated method stub
		return categoriadao.findByNombre(term);
	}

*/

}
