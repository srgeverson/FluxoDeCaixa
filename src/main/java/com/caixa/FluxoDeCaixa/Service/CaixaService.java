package com.caixa.FluxoDeCaixa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caixa.FluxoDeCaixa.DAO.CaixaDAO;
import com.caixa.FluxoDeCaixa.Models.CaixaModel;

@Service
public class CaixaService {

	@Autowired
	private CaixaDAO dao;

	public CaixaService(CaixaDAO dao) {
		super();
		this.dao = dao;
	}

	public List<CaixaModel> listarTodosCaixas() {
		return dao.findAll();
	}

	public List<CaixaModel> buscarPorTipo(String tipo) {
		return dao.findByTipoContainingIgnoreCase(tipo);
	}

	public boolean existe(Long id) {
		return dao.existsById(id);
	}

	public void apagar(Long id) {
		dao.deleteById(id);
	}

	public void salvar(CaixaModel caixa) {
		dao.save(caixa);
	}

	public CaixaModel buscarPorId(Long id) {
		return dao.getReferenceById(id);
	}
}
