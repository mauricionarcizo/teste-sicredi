package br.com.teste.sicredi.exceptions;

public class SessaoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SessaoNotFoundException(final String id) {
		super("Sessão não encontrada para o id: " + id);
	}
}
