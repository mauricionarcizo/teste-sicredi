package br.com.teste.sicredi.exceptions;

public class IdInvalidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdInvalidException(final String id) {
		super("Id: " + id + " é inválido");
	}
}
