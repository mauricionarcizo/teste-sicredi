package br.com.teste.sicredi.exceptions;

public class SessaoDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SessaoDuplicatedException(final String idSessao) {
		super("Sessao duplicada para o id: " + idSessao);
	}

}
