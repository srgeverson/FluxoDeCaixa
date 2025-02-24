package com.caixa.FluxoDeCaixa.Models.DTO;

public class OperacaoDTO {
	private String operacao;
	private String mensagemOperacao;
	private String detalhesOperacao;

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getMensagemOperacao() {
		return mensagemOperacao;
	}

	public void setMensagemOperacao(String mensagemOperacao) {
		this.mensagemOperacao = mensagemOperacao;
	}

	public String getDetalhesOperacao() {
		return detalhesOperacao;
	}

	public void setDetalhesOperacao(String detalhesOperacao) {
		this.detalhesOperacao = detalhesOperacao;
	}

}
