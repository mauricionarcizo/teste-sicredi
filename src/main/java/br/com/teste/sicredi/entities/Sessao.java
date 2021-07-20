package br.com.teste.sicredi.entities;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Document
public class Sessao {

	private static final Long DURACAO_DEFAULT = 60L;// 1 minuto

	// ID gerenciado pelo MongoDB
	@Id
	@JsonIgnore
	private String id;
	@ApiModelProperty(value = "Identificador da sessao")
	private String sessaoId;
	@ApiModelProperty(value = "Identificador da pauta desta sessao")
	@NotNull
	private String pautaId;
	@ApiModelProperty(value = "Duracao da votacao desta sessao")
	private Long duracao = DURACAO_DEFAULT;
	@JsonIgnore
	private SessaoVotacao votacao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPautaId() {
		return pautaId;
	}

	public void setPautaId(String pautaId) {
		this.pautaId = pautaId;
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}

	public SessaoVotacao getVotacao() {
		return votacao;
	}

	public void setVotacao(SessaoVotacao votacao) {
		this.votacao = votacao;
	}

	public String getSessaoId() {
		return sessaoId;
	}

	public void setSessaoId(String sessaoId) {
		this.sessaoId = sessaoId;
	}
}
