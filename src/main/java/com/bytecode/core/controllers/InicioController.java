package com.bytecode.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagina")
public class InicioController {
	
	@RequestMapping("/")
	public String index() {
		return "pagina/index";
	}
}


