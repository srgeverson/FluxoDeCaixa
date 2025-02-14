package com.caixa.FluxoDeCaixa.Models.Type;

public enum StatusEnum {
	INEXISTENTE(0),ATIVO(1), BLOQUEADO(2), CANCELADO(3);

	private final Integer valor;

	StatusEnum(Integer valor) {
		this.valor = valor;
	}

	public int getValor() {
		return this.valor;
	}
}
