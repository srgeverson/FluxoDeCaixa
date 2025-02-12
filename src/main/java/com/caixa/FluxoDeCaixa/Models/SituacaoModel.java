package com.caixa.FluxoDeCaixa.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.caixa.FluxoDeCaixa.Models.Type.StatusEnum;

@Entity
@Table(name = "situacoes")
public class SituacaoModel {

	@Id
	private StatusEnum id;

	@Column(nullable = false, unique = true, length = 100)
	private String descricao;

	@Column(name = "data_operacao", nullable = false)
	private LocalDateTime dataOperacao = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private UsuarioModel usuario;

	@OneToMany(mappedBy = "situacao")
	private List<UsuarioModel> usuarios;

	@OneToMany(mappedBy = "situacao")
	private List<CaixaModel> caixas;

	public StatusEnum getId() {
		return id;
	}

	public void setId(StatusEnum id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(LocalDateTime dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioModel> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioModel> usuarios) {
		this.usuarios = usuarios;
	}

	public List<CaixaModel> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<CaixaModel> caixas) {
		this.caixas = caixas;
	}

}
