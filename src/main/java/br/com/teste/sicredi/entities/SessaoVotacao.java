package br.com.teste.sicredi.entities;

import java.time.ZonedDateTime;

public class SessaoVotacao {

	private ZonedDateTime dataAbertura;
	private boolean encerrado;

	public ZonedDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(ZonedDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public boolean isEncerrado() {
		return encerrado;
	}

	public void setEncerrado(boolean encerrado) {
		this.encerrado = encerrado;
	}
}
