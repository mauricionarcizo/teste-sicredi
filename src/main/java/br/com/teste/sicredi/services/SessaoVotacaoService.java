package br.com.teste.sicredi.services;

import java.time.ZonedDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.entities.SessaoVotacao;
import br.com.teste.sicredi.exceptions.IdInvalidException;
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
		isIdValid(idSessao);
		final ObjectId objIdSessao = new ObjectId(idSessao);
		Sessao sessao = sessaoRepository.findById(objIdSessao).orElseThrow(() -> new SessaoNotFoundException(idSessao));

		if (sessao.getVotacao() != null) {
			throw new SessaoVotacaoAlreadyStartedException(idSessao);
		}
		if (sessao.getVotacao() != null && sessao.getVotacao().isEncerrado()) {
			throw new SessaoVotacaoFinishedException(idSessao);
		}

		SessaoVotacao sessaoVotacao = new SessaoVotacao();
		sessaoVotacao.setDataAbertura(ZonedDateTime.now());
		sessao.setVotacao(sessaoVotacao);
		Sessao s = sessaoRepository.save(sessao);
		scheduler.schedule(new SessaoVotacaoTask(s, sessaoRepository),
				new Date(System.currentTimeMillis() + sessao.getDuracao() * 1000));
		return s;
	}

	private void isIdValid(final String id) {
		if (!ObjectId.isValid(id)) {
			throw new IdInvalidException(id);
		}
	}
}
