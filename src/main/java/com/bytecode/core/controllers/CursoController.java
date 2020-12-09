package com.bytecode.core.controllers;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import com.bytecode.core.dao.CategoriaDao;
import com.bytecode.core.dao.ICursoDao;
import com.bytecode.core.models.entity.Curso;
import com.bytecode.core.service.CategoriaService;
import com.bytecode.core.service.CursoService;


@Controller
@RequestMapping("/cursos")
public class CursoController {
 
	//@Autowired
	//private ICursoDao cursodao;
	
	@Autowired
	@Qualifier("categoriaservice")
	private CategoriaService categoriaservice;
	
	@Autowired
	private CursoService cursoservice;
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Curso> cursos = cursoservice.getCursos();
		model.addAttribute("cursos", cursos);
		model.addAttribute("curso", new Curso());													
		model.addAttribute("pagina", 1);
		return "cursos/listar";
	}
	
	@RequestMapping("/form")
	public String crear(Map<String, Object> model, Model model2) {
		Curso curso = new Curso();

		//curso.setCategoria(categoria);
		model.put("titulo", "Registrar curso");
		model.put("curso", curso);
		model.put("pagina", 2);
		model2.addAttribute("categoria", categoriaservice.getCategoriaCursos());
		return "cursos/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Curso curso, BindingResult result, Model model) {
		//model.addAttribute("categoria", categoriadao.getCategoriaCursos());
		if (result.hasErrors()) {
			model.addAttribute("categoria", categoriaservice.getCategoriaCursos());
			return "cursos/form";
		}
		else {
			cursoservice.save(curso);			
		}
		
		return "redirect:listar";
	}
	
	@RequestMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Curso curso = null;
		if(id > 0) {
			curso = cursoservice.buscarCurso(id);
		}else {
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "Modificar Cliente");
		model.addAttribute("categoria", categoriaservice.getCategoriaCursos());
		model.addAttribute("curso",curso);
		return "cursos/form";
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id > 0) {
			cursoservice.eliminar(id);
		}
		return "redirect:/cursos/listar";
	}
	
	@RequestMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Curso curso = cursoservice.buscarCurso(id);
		if(curso == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/cursos/listar";
		}
		model.addAttribute("curso", curso);
		return "cursos/ver";
	}
	/*
	@RequestMapping(value = "/cargar-categorias/{term}", produces = {"application/json"})
	public @ResponseBody List<CategoriaCursos> cargarCategorias(@PathVariable String term){
		return cursodao.findByNombre(term);
	}
	*/
	
}
