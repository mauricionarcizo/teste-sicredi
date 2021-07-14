package br.com.teste.sicredi.exceptions;

public class EntidadeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EntidadeNotFoundException(final String id) {
		super("Entidade não encontrada para o id: " + id);
	}
}
