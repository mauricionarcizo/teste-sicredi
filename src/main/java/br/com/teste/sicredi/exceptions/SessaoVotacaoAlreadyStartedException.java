package br.com.teste.sicredi.exceptions;

public class SessaoVotacaoAlreadyStartedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SessaoVotacaoAlreadyStartedException(final String idSessao) {
		super("Sessão já foi iniciada para o id: " + idSessao);
	}

}
