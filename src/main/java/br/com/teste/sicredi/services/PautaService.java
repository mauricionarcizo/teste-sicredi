package br.com.teste.sicredi.services;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.teste.sicredi.entities.Pauta;
import br.com.teste.sicredi.exceptions.PautaDuplicatedException;
import br.com.teste.sicredi.exceptions.PautaNotFoundException;
import br.com.teste.sicredi.repositories.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	public List<Pauta> findAll() {
		return pautaRepository.findAll();
	}

	public Pauta get(final String pautaId) {
		return pautaRepository.findByPautaId(pautaId).orElseThrow(() -> new PautaNotFoundException(pautaId));
	}

	public Pauta add(final Pauta pauta) {
		if (ObjectUtils.isEmpty(pauta.getPautaId())) {
			pauta.setPautaId(UUID.randomUUID().toString());
		}else {
			pautaRepository.findByPautaId(pauta.getPautaId())
			.orElseThrow(() -> new PautaDuplicatedException(pauta.getPautaId()));
		}
		return pautaRepository.save(pauta);
	}

	public Pauta update(final Pauta pauta) {
		pautaRepository.findByPautaId(pauta.getPautaId())
				.orElseThrow(() -> new PautaNotFoundException(pauta.getPautaId()));
		return pautaRepository.save(pauta);
	}

	public void delete(final String pautaId) {
		Pauta pauta = pautaRepository.findByPautaId(pautaId).orElseThrow(() -> new PautaNotFoundException(pautaId));
		pautaRepository.deleteById(new ObjectId(pauta.getId()));
	}
}
