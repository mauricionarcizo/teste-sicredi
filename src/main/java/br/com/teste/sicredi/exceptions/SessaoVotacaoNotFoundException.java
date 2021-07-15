package br.com.teste.sicredi.exceptions;

public class SessaoVotacaoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SessaoVotacaoNotFoundException(final String id) {
		super("Votação na Sessão não encontrada para o id: " + id);
	}

}
