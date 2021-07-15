package br.com.teste.sicredi.exceptions;

public class AssociadoUnableToVoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AssociadoUnableToVoteException(final String cpf) {
		super("Associado de CPF: " + cpf + " não está habilitado para votar nesta sessão");
	}
}
