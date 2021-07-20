package br.com.teste.sicredi.dtos;

import br.com.teste.sicredi.entities.AssociadoVoto;

public class AssociadoVotosDTO {

	private String idSessao;
	private Long qtdAprova = 0L;
	private Long qtdReprova = 0L;
	private String resultado;

	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public Long getQtdAprova() {
		return qtdAprova;
	}

	public Long getQtdReprova() {
		return qtdReprova;
	}

	public void computarVoto(AssociadoVoto associadoVoto) {
		if (associadoVoto.getVoto()) {
			qtdAprova++;
		} else {
			qtdReprova++;
		}
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}
