package com.caixa.FluxoDeCaixa.Controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caixa.FluxoDeCaixa.Controllers.DTO.CaixaDTO;
import com.caixa.FluxoDeCaixa.Models.CaixaModel;
import com.caixa.FluxoDeCaixa.Models.Type.StatusEnum;
import com.caixa.FluxoDeCaixa.Service.CaixaService;

@Controller
public class HomeController {

	@Autowired
	private CaixaService service;
	private static final DecimalFormat FORMATAR_VALOR_EM_MOEDA = new DecimalFormat("#,##0.00");

	public HomeController(CaixaService service) {
		super();
		this.service = service;
	}

	@GetMapping("/")
	public String index(Model model, @RequestParam(required = false) String tipo) {
		var caixasDTO = new ArrayList<CaixaDTO>();
		List<CaixaModel> caixas;

		if (tipo == null || tipo.isEmpty())
			caixas = service.listarTodosCaixas();
		else
			caixas = service.buscarPorTipo(tipo);

		float totalReceitas = 0;
		float totalDespesas = 0;
		float totalBloqueios = 0;

		for (CaixaModel caixa : caixas) {
			var caixaDTO = new CaixaDTO();
			caixaDTO.setId(caixa.getId());
			caixaDTO.setTipo(caixa.getTipo());
			caixaDTO.setValor(FORMATAR_VALOR_EM_MOEDA.format(caixa.getValor()));
			caixaDTO.setStatus(caixa.getSituacao().getId());
			caixasDTO.add(caixaDTO);
			if (caixa.getSituacao().getId().equals(StatusEnum.ATIVO))
				totalReceitas += caixa.getValor();
			else if (caixa.getSituacao().getId().equals(StatusEnum.CANCELADO))
				totalDespesas += caixa.getValor();
			else if (caixa.getSituacao().getId().equals(StatusEnum.BLOQUEADO))
				totalBloqueios += caixa.getValor();
		}

		float valorTotal = totalReceitas - totalDespesas;

		String valorTotalFormatado = FORMATAR_VALOR_EM_MOEDA.format(valorTotal);
		String totalReceitasFormatado = FORMATAR_VALOR_EM_MOEDA.format(totalReceitas);
		String totalBloqueiosFormatado = FORMATAR_VALOR_EM_MOEDA.format(totalBloqueios);
		String totalDespesasFormatado = FORMATAR_VALOR_EM_MOEDA.format(totalDespesas);

		model.addAttribute("caixas", caixasDTO);
		model.addAttribute("valorTotal", valorTotalFormatado);
		model.addAttribute("totalReceitas", totalReceitasFormatado);
		model.addAttribute("totalBloqueios", totalBloqueiosFormatado);
		model.addAttribute("totalDespesas", totalDespesasFormatado);
		return "index";
	}
}
