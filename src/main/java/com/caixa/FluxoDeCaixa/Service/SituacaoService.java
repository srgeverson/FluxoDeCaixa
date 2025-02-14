package com.caixa.FluxoDeCaixa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caixa.FluxoDeCaixa.DAO.SituacaoDAO;
import com.caixa.FluxoDeCaixa.Models.SituacaoModel;

@Service
public class SituacaoService {

	@Autowired
	private SituacaoDAO dao;

	public SituacaoService(SituacaoDAO dao) {
		super();
		this.dao = dao;
	}

	public List<SituacaoModel> listarTudo() {
		return dao.findAll();
	}
}
