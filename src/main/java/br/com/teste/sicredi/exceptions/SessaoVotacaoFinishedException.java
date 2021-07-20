package br.com.teste.sicredi.exceptions;

public class SessaoVotacaoFinishedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SessaoVotacaoFinishedException(final String idSessao) {
		super("Votação da Sessão encerrada para o id: " + idSessao);
	}

}
