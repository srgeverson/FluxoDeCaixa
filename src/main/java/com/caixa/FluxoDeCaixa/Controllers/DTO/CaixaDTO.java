package com.caixa.FluxoDeCaixa.Controllers.DTO;

import com.caixa.FluxoDeCaixa.Models.Type.StatusEnum;

public class CaixaDTO {
	private Long id;
	private String tipo;
	private StatusEnum status;
	private String valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
