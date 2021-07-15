package br.com.teste.sicredi.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.exceptions.IdInvalidException;
import br.com.teste.sicredi.exceptions.PautaNotFoundException;
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

	public Sessao get(final String id) {
		isIdValid(id);
		return sessaoRepository.findById(new ObjectId(id)).orElseThrow(() -> new SessaoNotFoundException(id));
	}

	public Sessao add(final Sessao sessao) {

		pautaRepository.findById(new ObjectId(sessao.getPautaId()))
				.orElseThrow(() -> new PautaNotFoundException(sessao.getPautaId()));
		return sessaoRepository.save(sessao);
	}

	public Sessao update(final Sessao sessao) {
		isIdValid(sessao.getId());
		final ObjectId id = new ObjectId(sessao.getId());
		sessaoRepository.findById(id).orElseThrow(() -> new SessaoNotFoundException(sessao.getId()));
		return sessaoRepository.save(sessao);
	}

	public void delete(final String id) {
		isIdValid(id);
		ObjectId objId = new ObjectId(id);
		sessaoRepository.findById(objId).orElseThrow(() -> new SessaoNotFoundException(id));
		sessaoRepository.deleteById(objId);
	}

	private void isIdValid(final String id) {
		if (!ObjectId.isValid(id)) {
			throw new IdInvalidException(id);
		}
	}
}
