package com.caixa.FluxoDeCaixa.Controllers.V1;

import java.text.DecimalFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.caixa.FluxoDeCaixa.Controllers.DTO.CaixaDTO;
import com.caixa.FluxoDeCaixa.Models.CaixaModel;
import com.caixa.FluxoDeCaixa.Models.SituacaoModel;
import com.caixa.FluxoDeCaixa.Models.UsuarioModel;
import com.caixa.FluxoDeCaixa.Models.Type.StatusEnum;
import com.caixa.FluxoDeCaixa.Service.CaixaService;
import com.caixa.FluxoDeCaixa.Service.SituacaoService;

@Controller
@RequestMapping(path = "/v1/caixas")
public class CaixaController {

	@Autowired
	private CaixaService caixaService;
	private static final DecimalFormat FORMATAR_VALOR_EM_MOEDA = new DecimalFormat("#,##0.00");
	private SituacaoService situacaoService;
	private static final UsuarioModel USUARIO = new UsuarioModel(1L);

	public CaixaController(CaixaService service, SituacaoService situacaoService) {
		super();
		this.caixaService = service;
		this.situacaoService = situacaoService;
	}

	@GetMapping
	public String listar(Model model, @RequestParam(required = false) String tipo) {
		var caixasDTO = new ArrayList<CaixaDTO>();
		List<CaixaModel> caixas;

		if (tipo == null || tipo.isEmpty())
			caixas = caixaService.listarTodosCaixas();
		else
			caixas = caixaService.buscarPorTipo(tipo);

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
		return "home/index";
	}

	@GetMapping("/adicionar")
	public String adicionar(Model model) {
		var list = situacaoService.listarTudo();
		model.addAttribute("situacoes", list);
		return "home/adicionar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id) {
		if (caixaService.existe(id)) {
			caixaService.apagar(id);
		} else {
			System.out.println("Registro com ID " + id + " não encontrado.");
		}
		return "redirect:/";
	}

	@GetMapping("/editar/{id}")
	public String editar(Model model, @PathVariable Long id) {
		var caixa = caixaService.buscarPorId(id);
		model.addAttribute("caixa", caixa);
		var list = situacaoService.listarTudo();
		model.addAttribute("situacoes", list);
		return "home/editar";
	}

	@PostMapping("/cadastrar")
	public String cadastrar(@RequestParam("tipo") String tipo, @RequestParam("valor") String valor,
			@RequestParam("situacao") int situacao, Model model) {

		CaixaModel caixa = new CaixaModel();
		caixa.setTipo(tipo);
		caixa.setValor(Double.valueOf(valor.replace(".", "").replace(',', '.').trim()));
		var status = new SituacaoModel(StatusEnum.values()[situacao]);
		caixa.setSituacao(status);
		caixa.setUsuario(USUARIO);
		caixaService.salvar(caixa);

		return "redirect:/";
	}

	@PostMapping("/alterar")
	public String alterar(@RequestParam("id") Long id, @RequestParam("tipo") String tipo,
			@RequestParam("valor") String valor, @RequestParam("situacao") int situacao, Model model) {

		CaixaModel caixa = new CaixaModel();
		caixa.setId(id);
		caixa.setTipo(tipo);
		caixa.setValor(Double.valueOf(valor.replace(".", "").replace(',', '.').trim()));
		var status = new SituacaoModel(StatusEnum.values()[situacao]);
		caixa.setSituacao(status);
		caixa.setUsuario(USUARIO);
		caixa.setDataOperacao(OffsetDateTime.now());
		caixaService.salvar(caixa);

		return "redirect:/";
	}
}
