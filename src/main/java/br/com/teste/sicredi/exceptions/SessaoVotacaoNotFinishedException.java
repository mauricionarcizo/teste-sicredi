package br.com.teste.sicredi.exceptions;

public class SessaoVotacaoNotFinishedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SessaoVotacaoNotFinishedException(final String idSessao) {
		super("Votação da Sessão não foi encerrada para o id: " + idSessao);
	}

}
