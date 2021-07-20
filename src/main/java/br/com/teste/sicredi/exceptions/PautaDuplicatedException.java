package br.com.teste.sicredi.exceptions;

public class PautaDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PautaDuplicatedException(final String idSessao) {
		super("Pauta duplicada para o id: " + idSessao);
	}

}
