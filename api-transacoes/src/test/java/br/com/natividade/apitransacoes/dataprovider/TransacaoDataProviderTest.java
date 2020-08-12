package br.com.natividade.apitransacoes.dataprovider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.natividade.apitransacoes.model.TransacaoModel;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TransacaoDataProviderTest {

	@Autowired
	private TransacaoDataProvider dataProvider;

	@BeforeEach
	public void setUp() {
		dataProvider.deletarTransacoes();
	}
	
	@Test
	public void deveRealizarTransacao() {
		// cenário
		TransacaoModel transacao = new TransacaoModel(LocalDateTime.now(), new BigDecimal(1000));

		// ação
		dataProvider.realizarTransacao(transacao);

		// validação
		List<TransacaoModel> transacoes = dataProvider.getTransacoes();

		Assertions.assertEquals(1, transacoes.size());
		Assertions.assertEquals(transacao.getDataHora(), transacoes.get(0).getDataHora());
		Assertions.assertEquals(transacao.getValor(), transacoes.get(0).getValor());
	}

	@Test
	public void deveDeletarTransacoes() {
		// cenário
		TransacaoModel transacao = new TransacaoModel(LocalDateTime.now(), new BigDecimal(1000));
		
		dataProvider.realizarTransacao(transacao);
		int quantidadeTransacoesAntigas = dataProvider.getTransacoes().size();
		
		// ação
		dataProvider.deletarTransacoes();
		int quantidadeTransacoesAtuais = dataProvider.getTransacoes().size();
		
		
		// validação
		Assertions.assertEquals(1, quantidadeTransacoesAntigas);
		Assertions.assertEquals(0, quantidadeTransacoesAtuais);
	}
	
	@Test
	public void deveRetornarTransacoes() {
		// cenário
		TransacaoModel transacao = new TransacaoModel(LocalDateTime.now(), new BigDecimal(1000));
		TransacaoModel transacao2 = new TransacaoModel(LocalDateTime.of(2018, 5, 20, 14, 00), new BigDecimal(1000));
		
		dataProvider.realizarTransacao(transacao);
		dataProvider.realizarTransacao(transacao2);
		
		// ação
		List<TransacaoModel> transacoes = dataProvider.getTransacoes();
		
		// validação
		Assertions.assertEquals(2, transacoes.size());
		Assertions.assertEquals(2, transacoes.size());
		Assertions.assertEquals(transacao.getDataHora(), transacoes.get(0).getDataHora());
		Assertions.assertEquals(transacao.getValor(), transacoes.get(0).getValor());
		Assertions.assertEquals(transacao2.getDataHora(), transacoes.get(1).getDataHora());
		Assertions.assertEquals(transacao2.getValor(), transacoes.get(1).getValor());
	}

}
