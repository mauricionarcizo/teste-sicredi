package br.com.teste.sicredi.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.teste.sicredi.entities.Sessao;
import br.com.teste.sicredi.exceptions.SessaoNotFoundException;
import br.com.teste.sicredi.services.AssociadoVotoService;
import br.com.teste.sicredi.services.SessaoService;

@SpringBootTest
@AutoConfigureMockMvc
public class SessaoControllerTest {

	private static final String BASE_URI = "/sessao/v1.0";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SessaoService service;
	
	@MockBean
	private AssociadoVotoService associadoService;

	@Test
	public void listAll() throws Exception {

		mvc.perform(get(BASE_URI).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
	}

	@Test
	public void addSessao() throws Exception {

		Sessao sessao = new Sessao();
		sessao.setPautaId(UUID.randomUUID().toString());

		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(sessao))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getSessao() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setPautaId(UUID.randomUUID().toString());
		sessao.setSessaoId(UUID.randomUUID().toString());

		when(service.get(sessao.getSessaoId())).thenReturn(sessao);

		mvc.perform(get(BASE_URI + "/" + sessao.getSessaoId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.pautaId", equalTo(sessao.getPautaId())))
				.andExpect(jsonPath("$.sessaoId", equalTo(sessao.getSessaoId())));
	}

	@Test
	public void getSessaoNotExists() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setPautaId(UUID.randomUUID().toString());
		sessao.setSessaoId(UUID.randomUUID().toString());

		when(service.get(sessao.getSessaoId())).thenThrow(new SessaoNotFoundException(sessao.getSessaoId()));

		mvc.perform(get(BASE_URI + "/" + sessao.getSessaoId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void deleteSessao() throws Exception {
		Sessao sessao = new Sessao();
		sessao.setPautaId(UUID.randomUUID().toString());
		sessao.setSessaoId(UUID.randomUUID().toString());

		ObjectMapper mapper = new ObjectMapper();
		mvc.perform(delete(BASE_URI + "/" + sessao.getPautaId()).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(sessao))).andExpect(status().isOk());
	}

}
