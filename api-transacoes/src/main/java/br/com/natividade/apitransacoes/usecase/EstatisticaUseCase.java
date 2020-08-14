package br.com.natividade.apitransacoes.usecase;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.natividade.apitransacoes.model.EstatisticaModel;
import br.com.natividade.apitransacoes.model.TransacaoModel;
import br.com.natividade.apitransacoes.usecase.exception.TransacoesNaoEncontradaException;
import br.com.natividade.apitransacoes.usecase.gateway.TransacaoGateway;

@Component
public class EstatisticaUseCase {
	
	@Autowired
	private TransacaoGateway transacaoGateway;

	public EstatisticaModel getEstatistica() {

		List<TransacaoModel> transacoes = transacaoGateway.getTransacoes();
		
		if(transacoes.isEmpty()) {
			throw new TransacoesNaoEncontradaException("Nenhuma transação encontrada para obter a estatistica!");
		}
		
		List<TransacaoModel> transacoesFiltro = getTransacoesFiltroEstatistica(transacoes);

		EstatisticaModel estatistica = new EstatisticaModel();

		DoubleSummaryStatistics estatisticaSummary = new DoubleSummaryStatistics();

		synchronized (transacoesFiltro) {
			transacoesFiltro.forEach((transacao) -> {
				estatisticaSummary.accept(transacao.getValor().doubleValue());
			});
		}
		
		estatistica.setAvg(estatisticaSummary.getAverage());
		estatistica.setCount(estatisticaSummary.getCount());
		estatistica.setMax(estatisticaSummary.getMax());
		estatistica.setMin(estatisticaSummary.getMin());
		estatistica.setSum(estatisticaSummary.getSum());

		return estatistica;

	}
	
	List<TransacaoModel> getTransacoesFiltroEstatistica(List<TransacaoModel> transacoes) {

		final Long QUANTIDADE_SEGUNDOS_ESTATISTICA = 60L;

		List<TransacaoModel> transacoesFiltro = transacoes.stream().filter(
				(transacao) -> getDiferencaSegundosDataAtual(transacao.getDataHora()) <= QUANTIDADE_SEGUNDOS_ESTATISTICA)
				.collect(Collectors.toList());

		if (transacoesFiltro.isEmpty()) {
			throw new TransacoesNaoEncontradaException(
					"Nenhuma transação encontrada com base no filtro de " + QUANTIDADE_SEGUNDOS_ESTATISTICA + " segundos");
		}

		return transacoesFiltro;
	}
	
	private long getDiferencaSegundosDataAtual(LocalDateTime dataHoraTransacao) {
		return Duration.between(dataHoraTransacao, LocalDateTime.now()).getSeconds();
	}
}
