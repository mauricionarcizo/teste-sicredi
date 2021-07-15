package br.com.teste.sicredi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.sicredi.dtos.AssociadoVotosDTO;
import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.services.AssociadoVotoService;
import br.com.teste.sicredi.services.SessaoService;
import br.com.teste.sicredi.services.SessaoVotacaoService;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

	@Autowired
	private SessaoService service;

	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;

	@Autowired
	private AssociadoVotoService associadoVotoService;

	@GetMapping("/v1.0")
	public List<Sessao> list() {
		return service.findAll();
	}

	@GetMapping("/v1.0/{id}")
	public Sessao get(@PathVariable String id) {
		return service.get(id);
	}

	@PostMapping("/v1.0")
	public Sessao add(@RequestBody Sessao pauta) {
		return service.add(pauta);
	}

	@DeleteMapping("/v1.0/{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

	@PostMapping("/v1.0/iniciar/{id}")
	public Sessao iniciarSessao(@PathVariable("id") String idSessao) {
		return sessaoVotacaoService.iniciarSessao(idSessao);
	}

	@PostMapping("/v1.0/votacao/{id}/{cpf}/{aceitaPauta}")
	public void sessaoVotacao(@PathVariable("id") String idSessao, @PathVariable("cpf") String cpf,
			@PathVariable("aceitaPauta") boolean aceitaPauta) {
		associadoVotoService.sessaoVotacaoVotar(idSessao, cpf, aceitaPauta);
	}

	@GetMapping("/v1.0/contabilizar/{id}")
	public AssociadoVotosDTO contabilizarVotos(@PathVariable("id") String idSessao) {
		return associadoVotoService.contabilizarVotos(idSessao);
	}
}
