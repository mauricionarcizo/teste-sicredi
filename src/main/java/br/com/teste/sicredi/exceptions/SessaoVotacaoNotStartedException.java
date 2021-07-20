package br.com.teste.sicredi.exceptions;

public class SessaoVotacaoNotStartedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SessaoVotacaoNotStartedException(final String idSessaoVotacao) {
		super("Votação na Sessão não iniciada para o id: " + idSessaoVotacao);
	}
}
