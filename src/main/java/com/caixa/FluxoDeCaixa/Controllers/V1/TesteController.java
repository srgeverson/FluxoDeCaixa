package com.caixa.FluxoDeCaixa.Controllers.V1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TesteController {
	@GetMapping("/v1/testes")
	public String teste(Model model, @RequestParam(required = false) String tipo) {
		return "testes/index";
	}
}
