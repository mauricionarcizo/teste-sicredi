package br.com.teste.sicredi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sessao {
	
	private static final Long DURACAO_DEFAULT= 60L;//1 minuto

	@Id
	private String id;
	private String pautaId;
	private Long duracao = DURACAO_DEFAULT;
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
}
