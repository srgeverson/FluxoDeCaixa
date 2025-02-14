package com.caixa.FluxoDeCaixa.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.caixa.FluxoDeCaixa.Models.CaixaModel;

@Repository
public interface CaixaDAO extends CustomJpaDAO<CaixaModel, Long> {

	List<CaixaModel> findByTipoContainingIgnoreCase(String tipo);

}
