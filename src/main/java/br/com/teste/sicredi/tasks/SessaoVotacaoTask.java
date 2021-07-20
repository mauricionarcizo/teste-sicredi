package br.com.teste.sicredi.tasks;

import org.bson.types.ObjectId;

import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.exceptions.SessaoVotacaoNotFoundException;
import br.com.teste.sicredi.repositories.SessaoRepository;

public class SessaoVotacaoTask implements Runnable {

	private final Sessao sessao;
	private SessaoRepository sessaoRepository;

	public SessaoVotacaoTask(final Sessao sessao, final SessaoRepository sessaoRepository) {
		this.sessao = sessao;
		this.sessaoRepository = sessaoRepository;
	}

	@Override
	public void run() {
		Sessao sessao = sessaoRepository.findById(new ObjectId(this.sessao.getId()))
				.orElseThrow(() -> new SessaoVotacaoNotFoundException(this.sessao.getId()));
		sessao.getVotacao().setEncerrado(true);
		sessaoRepository.save(sessao);
	}
}
