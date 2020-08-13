package br.com.natividade.apitransacoes.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.natividade.apitransacoes.dataprovider.TransacaoDataProvider;
import br.com.natividade.apitransacoes.model.TransacaoModel;
import br.com.natividade.apitransacoes.usecase.EstatisticaUseCase;
import br.com.natividade.apitransacoes.usecase.gateway.TransacaoGateway;

@SpringBootTest
@AutoConfigureMockMvc
public class EstatisticaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TransacaoDataProvider transacaoDataProvider;

	@InjectMocks
	private EstatisticaUseCase usecase;

	@Mock
	private TransacaoGateway gateway;

	@BeforeEach
	void setUp() {
		transacaoDataProvider.deletarTransacoes();
	}

	@Test
	public void deveObterEstatistica() throws Exception {
		// ação
		transacaoDataProvider
				.realizarTransacao(new TransacaoModel(LocalDateTime.now().minusSeconds(30L), new BigDecimal(5000)));
		transacaoDataProvider
				.realizarTransacao(new TransacaoModel(LocalDateTime.now().minusSeconds(30L), new BigDecimal(500)));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/estatistica"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.count", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.avg", Matchers.is(2750.0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.sum", Matchers.is(5500)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.min", Matchers.is(500)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.max", Matchers.is(5000)));

	}

	@Test
	public void deveReceberStatusCode404() throws Exception {
		// ação e validação
		MvcResult retorno = this.mockMvc.perform(MockMvcRequestBuilders.get("/estatistica"))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		String resposta = retorno.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(resposta.contains("Nenhuma transação encontrada para obter a estatistica!"));

	}

	@Test
	public void deveReceberStatusCode404PorNaoTerDadosGerarEstatistica() throws Exception {
		// ação e validação
		transacaoDataProvider.realizarTransacao(new TransacaoModel(LocalDateTime.MIN, new BigDecimal(1000)));

		MvcResult retorno = this.mockMvc.perform(MockMvcRequestBuilders.get("/estatistica"))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		String resposta = retorno.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(resposta.contains("Nenhuma transação encontrada com base no filtro de"));

	}
}
