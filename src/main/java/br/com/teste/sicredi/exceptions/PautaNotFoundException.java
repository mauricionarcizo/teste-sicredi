package br.com.teste.sicredi.exceptions;

public class PautaNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PautaNotFoundException(final String id) {
		super("Pauta não encontrada para o id: " + id);
	}
}
