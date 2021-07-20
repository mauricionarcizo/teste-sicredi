package br.com.teste.sicredi.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import br.com.teste.sicredi.entities.Pauta;
import br.com.teste.sicredi.exceptions.PautaNotFoundException;
import br.com.teste.sicredi.services.PautaService;

@SpringBootTest
@AutoConfigureMockMvc
public class PautaControllerTest {

	private static final String BASE_URI = "/pauta/v1.0";

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PautaService service;

	@Test
	public void listAll() throws Exception {

		mvc.perform(get(BASE_URI).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
	}

	@Test
	public void addPauta() throws Exception {

		Pauta pauta = new Pauta();
		pauta.setDescricao("Teste pauta");

		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post(BASE_URI).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(pauta))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getPauta() throws Exception {
		Pauta pauta = new Pauta();
		pauta.setDescricao("Teste pauta");
		pauta.setPautaId(UUID.randomUUID().toString());

		when(service.get(pauta.getPautaId())).thenReturn(pauta);

		mvc.perform(get(BASE_URI + "/" + pauta.getPautaId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.pautaId", equalTo(pauta.getPautaId())))
				.andExpect(jsonPath("$.descricao", equalTo(pauta.getDescricao())));
	}

	@Test
	public void getPautaNotExists() throws Exception {
		Pauta pauta = new Pauta();
		pauta.setDescricao("Teste pauta");
		pauta.setPautaId(UUID.randomUUID().toString());

		when(service.get(pauta.getPautaId())).thenThrow(new PautaNotFoundException(pauta.getPautaId()));

		mvc.perform(get(BASE_URI + "/" + pauta.getPautaId()).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void updatePauta() throws Exception {
		Pauta pauta = new Pauta();
		pauta.setDescricao("Teste pauta");
		pauta.setPautaId(UUID.randomUUID().toString());

		when(service.update(pauta)).thenReturn(pauta);

		ObjectMapper mapper = new ObjectMapper();
		mvc.perform(put(BASE_URI).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(pauta)))
				.andExpect(status().isOk());
	}

	@Test
	public void deletePauta() throws Exception {
		Pauta pauta = new Pauta();
		pauta.setDescricao("Teste pauta");
		pauta.setPautaId(UUID.randomUUID().toString());

		ObjectMapper mapper = new ObjectMapper();
		mvc.perform(delete(BASE_URI + "/" + pauta.getPautaId()).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(pauta))).andExpect(status().isOk());
	}

}
