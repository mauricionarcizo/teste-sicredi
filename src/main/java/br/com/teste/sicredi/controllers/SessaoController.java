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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

	@Autowired
	private SessaoService service;

	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;

	@Autowired
	private AssociadoVotoService associadoVotoService;

	@ApiOperation(value = "Retorna a lista de sessoes")
	@GetMapping("/v1.0")
	public List<Sessao> list() {
		return service.findAll();
	}

	@ApiOperation(value = "Retorna uma Sessao")
	@GetMapping("/v1.0/{id}")
	public Sessao get(@PathVariable String id) {
		return service.get(id);
	}

	@ApiOperation(value = "Cria uma Sessao")
	@PostMapping("/v1.0")
	public Sessao add(@RequestBody Sessao pauta) {
		return service.add(pauta);
	}

	@ApiOperation(value = "Remove uma Sessao")
	@DeleteMapping("/v1.0/{id}")
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}

	@ApiOperation(value = "Inicia a votacao de uma Sessao")
	@PostMapping("/v1.0/iniciar/{id}")
	public Sessao iniciarSessao(@PathVariable("id") String idSessao) {
		return sessaoVotacaoService.iniciarSessao(idSessao);
	}

	@ApiOperation(value = "Registro o voto de um associado em uma Sessao")
	@PostMapping("/v1.0/votacao/{id}/{cpf}/{aceitaPauta}")
	public void sessaoVotacao(@PathVariable("id") String idSessao, @PathVariable("cpf") String cpf,
			@PathVariable("aceitaPauta") boolean aceitaPauta) {
		associadoVotoService.sessaoVotacaoVotar(idSessao, cpf, aceitaPauta);
	}

	@ApiOperation(value = "Obter o resultado da votacao na Sessao")
	@GetMapping("/v1.0/contabilizar/{id}")
	public AssociadoVotosDTO contabilizarVotos(@PathVariable("id") String idSessao) {
		return associadoVotoService.contabilizarVotos(idSessao);
	}
}
