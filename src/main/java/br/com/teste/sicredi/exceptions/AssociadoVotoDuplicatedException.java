package br.com.teste.sicredi.exceptions;

public class AssociadoVotoDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AssociadoVotoDuplicatedException(final String idSessao, final String cpf) {
		super("Voto duplicado para o cpf: " + cpf + " na sessão: " + idSessao);
	}

}
