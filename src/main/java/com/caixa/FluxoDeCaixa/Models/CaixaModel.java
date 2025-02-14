package com.caixa.FluxoDeCaixa.Models;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "caixas")
public class CaixaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String tipo;

    @Column
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "situacao", nullable = false)
    private SituacaoModel situacao;

    @Column(name = "data_operacao", nullable = false)
    private OffsetDateTime dataOperacao = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioModel usuario;

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

	public Double getValor() {
		if (valor == null)
			return 0.0;
		else
			return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public SituacaoModel getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoModel situacao) {
		this.situacao = situacao;
	}

	public OffsetDateTime getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(OffsetDateTime dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

}
