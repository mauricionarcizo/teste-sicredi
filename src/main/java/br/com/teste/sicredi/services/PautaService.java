package br.com.teste.sicredi.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.sicredi.entities.Pauta;
import br.com.teste.sicredi.exceptions.PautaNotFoundException;
import br.com.teste.sicredi.exceptions.IdInvalidException;
import br.com.teste.sicredi.repositories.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	public List<Pauta> findAll() {
		return pautaRepository.findAll();
	}

	public Pauta get(final String id) {
		isIdValid(id);
		return pautaRepository.findById(new ObjectId(id)).orElseThrow(() -> new PautaNotFoundException(id));
	}

	public Pauta add(final Pauta pauta) {
		return pautaRepository.save(pauta);
	}

	public Pauta update(final Pauta pauta) {
		isIdValid(pauta.getId());
		final ObjectId id = new ObjectId(pauta.getId());
		pautaRepository.findById(id).orElseThrow(() -> new PautaNotFoundException(pauta.getId()));
		return pautaRepository.save(pauta);
	}

	public void delete(final String id) {
		isIdValid(id);
		ObjectId objId = new ObjectId(id);
		pautaRepository.findById(objId).orElseThrow(() -> new PautaNotFoundException(id));
		pautaRepository.deleteById(objId);
	}

	private void isIdValid(final String id) {
		if (!ObjectId.isValid(id)) {
			throw new IdInvalidException(id);
		}
	}
}
