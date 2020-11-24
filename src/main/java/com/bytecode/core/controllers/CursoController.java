package com.bytecode.core.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bytecode.core.dao.ICursoDao;
import com.bytecode.core.models.entity.Curso;


@Controller
@RequestMapping("/cursos")
public class CursoController {
 
	@Autowired
	private ICursoDao cursodao;
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("cursos", cursodao.getCursos());
		model.addAttribute("pagina", 1);
		return "cursos/listar";
	}
	
	@RequestMapping("/form")
	public String crear(Map<String, Object> model) {
		Curso curso = new Curso();
		model.put("titulo", "Registrar curso");
		model.put("curso", curso);
		model.put("pagina", 2);
		return "cursos/form";
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(Curso curso) {
		cursodao.save(curso);
		return "redirect:listar";
	}
	
	@RequestMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Curso curso = null;
		if(id > 0) {
			curso = cursodao.buscarCurso(id);
		}else {
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "Modificar Cliente");
		model.addAttribute("curso",curso);
		return "cursos/form";
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id > 0) {
			cursodao.eliminar(id);
		}
		return "redirect:/cursos/listar";
	}
	
	@RequestMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Curso curso = cursodao.buscarCurso(id);
		if(curso == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/cursos/listar";
		}
		model.addAttribute("curso", curso);
		return "cursos/ver";
	}
}
