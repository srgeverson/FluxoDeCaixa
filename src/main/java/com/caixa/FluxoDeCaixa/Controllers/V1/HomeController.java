package com.caixa.FluxoDeCaixa.Controllers.V1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/v1")
public class HomeController {

	public HomeController() {
		super();
	}

	@GetMapping
	public String index(Model model, @RequestParam(required = false) String buscar) {
		return "main";
	}
}
