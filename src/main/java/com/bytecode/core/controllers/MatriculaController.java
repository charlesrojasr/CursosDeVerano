package com.bytecode.core.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bytecode.core.dao.IAlumnoDao;
import com.bytecode.core.models.entity.Alumno;
import com.bytecode.core.models.entity.Curso;
import com.bytecode.core.models.entity.DetalleMatricula;
import com.bytecode.core.models.entity.Matricula;


@Controller
@RequestMapping("/matricula")
@SessionAttributes("matricula")
public class MatriculaController {
	
	@Autowired
	private IAlumnoDao alumnodao;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
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
	
	@RequestMapping(value="/cargar-cursos/{term}", produces = {"application/json"})
	public @ResponseBody List<Curso> cargarCursos(@PathVariable String term){
			return alumnodao.findByNombre(term);
	}
	
	@PostMapping("/form")
	public String guardar(Matricula matricula, 
			@RequestParam(name = "item_id[]", required = true) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = true) Integer[] cantidad,
			RedirectAttributes flash,
			SessionStatus status) {
			
			for(int i=0; i<itemId.length; i++) {
				Curso c = alumnodao.findCursoById(itemId[i]);
				
				DetalleMatricula linea = new DetalleMatricula();
				linea.setCantidad(cantidad[i]);
				linea.setCurso(c);
				matricula.addDetalleMatricula(linea);
				log.info("ID: "+itemId[i].toString()+ " cantidad: "+cantidad[i].toString());
			}
			alumnodao.saveMatricula(matricula);
			status.setComplete();
			
			flash.addFlashAttribute("success", "Matricula creada con éxito");			
		
		return "redirect:/ver/"+matricula.getAlumno().getId();
	}
}
