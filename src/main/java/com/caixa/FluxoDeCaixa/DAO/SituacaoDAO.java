package com.caixa.FluxoDeCaixa.DAO;

import org.springframework.stereotype.Repository;

import com.caixa.FluxoDeCaixa.Models.SituacaoModel;
import com.caixa.FluxoDeCaixa.Models.Type.StatusEnum;

@Repository
public interface SituacaoDAO extends CustomJpaDAO<SituacaoModel, StatusEnum> {

}
