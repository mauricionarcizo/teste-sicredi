package br.com.teste.sicredi.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.sicredi.entities.Pauta;
import br.com.teste.sicredi.exceptions.EntidadeNotFoundException;
import br.com.teste.sicredi.repositories.PautaRepository;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaRepository pautaRepository;

	@GetMapping("/v1.0")
	public List<Pauta> list() {
		return pautaRepository.findAll();
	}

	@GetMapping("/v1.0/{id}")
	public Pauta list(@PathVariable String id) {
		return pautaRepository.findById(new ObjectId(id)).orElseThrow(() -> new EntidadeNotFoundException(id));
	}

	@PostMapping("/v1.0")
	public Pauta add(@RequestBody Pauta pauta) {
		return pautaRepository.save(pauta);
	}

	@PutMapping("/v1.0")
	public Pauta update(@RequestBody Pauta pauta) {
		final ObjectId id = new ObjectId(pauta.getId());
		pautaRepository.findById(id).orElseThrow(() -> new EntidadeNotFoundException(pauta.getId()));
		return pautaRepository.save(pauta);
	}

	@DeleteMapping("/v1.0/{id}")
	public void delete(@PathVariable("id") String id) {
		ObjectId objId = new ObjectId(id);
		pautaRepository.findById(objId).orElseThrow(() -> new EntidadeNotFoundException(id));
		pautaRepository.deleteById(objId);
	}
}
