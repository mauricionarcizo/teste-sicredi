package br.com.teste.sicredi.services;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.entities.SessaoVotacao;
import br.com.teste.sicredi.exceptions.SessaoNotFoundException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoAlreadyStartedException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoFinishedException;
import br.com.teste.sicredi.repositories.SessaoRepository;
import br.com.teste.sicredi.tasks.SessaoVotacaoTask;

@Service
public class SessaoVotacaoService {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private ThreadPoolTaskScheduler scheduler;

	public Sessao iniciarSessao(String idSessao) {
		Sessao sessao = sessaoRepository.findBySessaoId(idSessao)
				.orElseThrow(() -> new SessaoNotFoundException(idSessao));

		if (sessao.getVotacao() != null) {
			if (sessao.getVotacao().isEncerrado()) {
				throw new SessaoVotacaoFinishedException(idSessao);
			}
			throw new SessaoVotacaoAlreadyStartedException(idSessao);
		}

		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		sessaoVotacao.setDataAbertura(ZonedDateTime.now());
		sessao.setVotacao(sessaoVotacao);
		Sessao s = sessaoRepository.save(sessao);
		scheduler.schedule(new SessaoVotacaoTask(s, sessaoRepository),
				new Date(System.currentTimeMillis() + sessao.getDuracao() * 1000));
		return s;
	}

}
