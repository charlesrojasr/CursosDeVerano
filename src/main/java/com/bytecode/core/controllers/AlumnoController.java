package com.bytecode.core.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bytecode.core.dao.IAlumnoDao;
import com.bytecode.core.models.entity.Alumno;


@Controller
public class AlumnoController {
	
	@Autowired
	private IAlumnoDao alumnodao;
	
	@RequestMapping("/alumnos/listar")
	public String listar(Model model) {
		model.addAttribute("alumnos", alumnodao.getAlumnos());
		return "/alumnos/listar";
	}
	
	@RequestMapping("/alumnos/formulario")
	public String crear(Map<String, Object> model) {
		Alumno alumno = new Alumno();
		model.put("titulo", "Registrar Alumno");
		model.put("guardar", "Registrar");
		model.put("alumno", alumno);
		return "/alumnos/formulario";
	}
	
	@RequestMapping(value = "/alumnos/formulario", method = RequestMethod.POST)
	public String guardar(Alumno alumno) {
		alumnodao.save(alumno);
		return "redirect:/alumnos/listar";
	}
	
	@RequestMapping("/formulario/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Alumno alumno = null;
		if(id > 0) {
			alumno = alumnodao.buscarAlumno(id);
		}
		else {
			return "redirect:/alumnos/listar";
		}
		model.addAttribute("titulo", "Editar Alumno");
		model.addAttribute("guardar", "Actualizar");
		model.addAttribute("alumno", alumno);
		return "/alumnos/formulario";
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id > 0) {
			alumnodao.eliminar(id);
		}
		return "redirect:/alumnos/listar";
	}
	
	@RequestMapping("/ver/{id}")
	public String ver(@PathVariable( value = "id")Long id, Model model, RedirectAttributes flash) {
		Alumno alumno = alumnodao.buscarAlumno(id);
		if(alumno == null) {
			flash.addFlashAttribute("error", "El alumno no existe");
			return "redirect:/alumnos/listar";
		}
		model.addAttribute("alumno", alumno);
		return "/alumnos/ver";
	}
	
	
	
	
}
