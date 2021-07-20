package br.com.teste.sicredi.services;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.exceptions.PautaNotFoundException;
import br.com.teste.sicredi.exceptions.SessaoDuplicatedException;
import br.com.teste.sicredi.exceptions.SessaoNotFoundException;
import br.com.teste.sicredi.repositories.PautaRepository;
import br.com.teste.sicredi.repositories.SessaoRepository;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	public List<Sessao> findAll() {
		return sessaoRepository.findAll();
	}

	public Sessao get(final String sessaoId) {
		return sessaoRepository.findBySessaoId(sessaoId).orElseThrow(() -> new SessaoNotFoundException(sessaoId));
	}

	public Sessao add(final Sessao sessao) {

		pautaRepository.findByPautaId(sessao.getPautaId())
				.orElseThrow(() -> new PautaNotFoundException(sessao.getPautaId()));

		if (ObjectUtils.isEmpty(sessao.getSessaoId())) {
			sessao.setSessaoId(UUID.randomUUID().toString());
		} else {
			if (sessaoRepository.findBySessaoId(sessao.getSessaoId()).isPresent()) {
				new SessaoDuplicatedException(sessao.getSessaoId());
			}
		}
		return sessaoRepository.save(sessao);
	}

	public Sessao update(final Sessao sessao) {
		sessaoRepository.findBySessaoId(sessao.getSessaoId())
				.orElseThrow(() -> new SessaoNotFoundException(sessao.getSessaoId()));
		return sessaoRepository.save(sessao);
	}

	public void delete(final String sessaoId) {
		Sessao sessao = sessaoRepository.findBySessaoId(sessaoId)
				.orElseThrow(() -> new SessaoNotFoundException(sessaoId));
		sessaoRepository.deleteById(new ObjectId(sessao.getId()));
	}

}
