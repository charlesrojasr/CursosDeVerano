package com.bytecode.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bytecode.core.dao.IAlumnoDao;
import com.bytecode.core.models.entity.Alumno;
import com.bytecode.core.models.entity.Matricula;

@Controller
@RequestMapping("/matricula")
public class MatriculaController {
	
	@Autowired
	private IAlumnoDao alumnodao;
	
	@RequestMapping("/form/{alumnoId}")
	public String crear(@PathVariable(value = "alumnoId")Long alumnoId, 
			Model model, RedirectAttributes flash) {
		
		Alumno alumno = alumnodao.buscarAlumno(alumnoId);
		if(alumno == null) {
			flash.addFlashAttribute("error","El alumno no existe");
			return "redirect:/alumnos/listar";
		}
		Matricula matricula = new Matricula();
		matricula.setAlumno(alumno);
		model.addAttribute("matricula", matricula);
		return "matriculas/formulario";
	}
	
}
