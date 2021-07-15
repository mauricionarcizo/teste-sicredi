package br.com.teste.sicredi.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.teste.sicredi.dtos.AssociadoVotosDTO;
import br.com.teste.sicredi.entities.AssociadoVoto;
import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.exceptions.AssociadoUnableToVoteException;
import br.com.teste.sicredi.exceptions.AssociadoVotoDuplicatedException;
import br.com.teste.sicredi.exceptions.CpfInvalidException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoFinishedException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoNotFinishedException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoNotFoundException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoNotStartedException;
import br.com.teste.sicredi.repositories.AssociadoVotoRepository;
import br.com.teste.sicredi.repositories.SessaoRepository;

@Service
public class AssociadoVotoService {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Autowired
	private AssociadoVotoRepository associadoVotoRepository;

	@Autowired
	private RestTemplate restTemplate;

	public void sessaoVotacaoVotar(final String idSessao, final String cpf, final boolean aceitaPauta) {
		Sessao sessao = sessaoRepository.findById(new ObjectId(idSessao))
				.orElseThrow(() -> new SessaoVotacaoNotFoundException(idSessao));

		if (sessao.getVotacao() == null || sessao.getVotacao().getDataAbertura() == null) {
			throw new SessaoVotacaoNotStartedException(idSessao);
		}
		if (sessao.getVotacao().isEncerrado()) {
			throw new SessaoVotacaoFinishedException(idSessao);
		}
		if (associadoVotoRepository.existsVotoComputado(idSessao, cpf)) {
			throw new AssociadoVotoDuplicatedException(idSessao, cpf);
		}

		cpfIsAbleToVote(cpf);

		AssociadoVoto voto = new AssociadoVoto();
		voto.setCpf(cpf);
		voto.setVoto(aceitaPauta);
		voto.setIdSessao(idSessao);
		associadoVotoRepository.save(voto);
	}

	public AssociadoVotosDTO contabilizarVotos(String idSessao) {
		Sessao sessao = sessaoRepository.findById(new ObjectId(idSessao))
				.orElseThrow(() -> new SessaoVotacaoNotFoundException(idSessao));
		if (sessao.getVotacao() == null || sessao.getVotacao().getDataAbertura() == null) {
			throw new SessaoVotacaoNotStartedException(idSessao);
		}
		if (!sessao.getVotacao().isEncerrado()) {
			throw new SessaoVotacaoNotFinishedException(idSessao);
		}

		List<AssociadoVoto> votos = associadoVotoRepository.findAllByIdSessao(idSessao);
		AssociadoVotosDTO dto = new AssociadoVotosDTO();
		dto.setIdSessao(idSessao);
		votos.forEach(voto -> dto.computarVoto(voto));

		if (dto.getQtdAprova() > dto.getQtdReprova()) {
			dto.setResultado("A maioria dos associados aprovam a pauta.");
		} else if (dto.getQtdReprova() > dto.getQtdAprova()) {
			dto.setResultado("A maioria dos associados reprovam a pauta.");
		} else {
			dto.setResultado("O resultado da votação ficou empatada.");
		}

		return dto;
	}

	private void cpfIsAbleToVote(final String cpf) {
		String uri = "https://user-info.herokuapp.com/users/" + cpf;
		try {

			AssociadoHabilVoto associadoHabilVoto = restTemplate.getForObject(uri, AssociadoHabilVoto.class);
			if ("UNABLE_TO_VOTE".equals(associadoHabilVoto.getStatus())) {
				throw new AssociadoUnableToVoteException(cpf);
			}
		} catch (HttpClientErrorException ex) {
			throw new CpfInvalidException(cpf);
		}
	}

	private static class AssociadoHabilVoto {
		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	}
}
