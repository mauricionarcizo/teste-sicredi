package br.com.teste.sicredi.entities;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Document
public class Pauta {

	// ID gerenciado pelo MongoDB
	@Id
	@JsonIgnore
	private String id;

	@ApiModelProperty(value = "Identificador da pauta")
	private String pautaId;

	@ApiModelProperty(value = "Descricao da pauta")
	@NotNull
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}