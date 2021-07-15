package br.com.teste.sicredi.controllers;

import java.util.List;

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
import br.com.teste.sicredi.services.PautaService;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private PautaService service;

	@GetMapping("/v1.0")
	public List<Pauta> list() {
		return service.findAll();
	}

	@GetMapping("/v1.0/{id}")
	public Pauta get(@PathVariable String id) {
		return service.get(id);
	}

	@PostMapping("/v1.0")
	public Pauta add(@RequestBody Pauta pauta) {
		return service.add(pauta);
	}

	@PutMapping("/v1.0")
	public Pauta update(@RequestBody Pauta pauta) {
		return service.update(pauta);
	}

	@DeleteMapping("/v1.0/{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
}
