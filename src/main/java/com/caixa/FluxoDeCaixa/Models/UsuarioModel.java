package com.caixa.FluxoDeCaixa.Models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 80)
	private String nome;

	@Column(nullable = false, unique = true, length = 255)
	private String email;

	@Column(length = 255)
	private String senha;

	@ManyToOne
	@JoinColumn(name = "situacao", nullable = false)
	private SituacaoModel situacao;

	@Column(name = "codigo_acesso", length = 80)
	private String codigoAcesso;

	@Column(name = "data_operacao", nullable = false)
	private OffsetDateTime dataOperacao = OffsetDateTime.now();

	@Column(name = "data_ultimo_acesso")
	private OffsetDateTime dataUltimoAcesso;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private UsuarioModel usuario;

	@OneToMany(mappedBy = "usuario")
	private List<UsuarioModel> subordinados;

	@OneToMany(mappedBy = "usuario")
	private List<CaixaModel> caixas;

	@OneToMany(mappedBy = "usuario")
	private List<SituacaoModel> situacoesCriadas;

	public UsuarioModel() {
	}
	
	public UsuarioModel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public SituacaoModel getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoModel situacao) {
		this.situacao = situacao;
	}

	public String getCodigoAcesso() {
		return codigoAcesso;
	}

	public void setCodigoAcesso(String codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

	public OffsetDateTime getDataOperacao() {
		return dataOperacao;
	}

	public void setDataOperacao(OffsetDateTime dataOperacao) {
		this.dataOperacao = dataOperacao;
	}

	public OffsetDateTime getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(OffsetDateTime dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioModel> getSubordinados() {
		return subordinados;
	}

	public void setSubordinados(List<UsuarioModel> subordinados) {
		this.subordinados = subordinados;
	}

	public List<CaixaModel> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<CaixaModel> caixas) {
		this.caixas = caixas;
	}

	public List<SituacaoModel> getSituacoesCriadas() {
		return situacoesCriadas;
	}

	public void setSituacoesCriadas(List<SituacaoModel> situacoesCriadas) {
		this.situacoesCriadas = situacoesCriadas;
	}

}
