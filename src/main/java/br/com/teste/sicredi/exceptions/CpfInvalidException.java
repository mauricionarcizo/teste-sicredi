package br.com.teste.sicredi.exceptions;

public class CpfInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfInvalidException(final String cpf) {
		super("CPF " + cpf + " é inválido");
	}

}
