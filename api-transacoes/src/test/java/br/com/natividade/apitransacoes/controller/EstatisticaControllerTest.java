package br.com.natividade.apitransacoes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.natividade.apitransacoes.usecase.EstatisticaUseCase;

@SpringBootTest
@AutoConfigureMockMvc
public class EstatisticaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private EstatisticaUseCase estatisticaUseCase;

	@Test
	public void deveObterEstatistica() throws Exception {
		// ação
		this.mockMvc.perform(MockMvcRequestBuilders
	            .get("/estatistica")
	            .accept("*/*"))
				.andExpect(MockMvcResultMatchers.model().size(1))
	            .andExpect(MockMvcResultMatchers.status().isOk());
	}
}
