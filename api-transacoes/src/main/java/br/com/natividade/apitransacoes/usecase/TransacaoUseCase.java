package br.com.natividade.apitransacoes.usecase;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.natividade.apitransacoes.model.EstatisticaModel;
import br.com.natividade.apitransacoes.model.TransacaoModel;
import br.com.natividade.apitransacoes.usecase.exception.TransacoesNaoEncontradaException;
import br.com.natividade.apitransacoes.usecase.gateway.TransacaoGateway;

@Component
public class TransacaoUseCase {

	@Autowired
	private TransacaoGateway transacaoGateway;

	public void realizarTransacao(TransacaoModel transacao) {
		transacaoGateway.realizarTransacao(transacao);
	}

	public void deletarTransacoes() {

		verificaExistenciaTransacoes("Nenhuma transação encontrada para ser deletada!");

		transacaoGateway.deletarTransacoes();
	}

	public EstatisticaModel getEstatistica() {

		verificaExistenciaTransacoes("Nenhuma transação encontrada para obter a estatistica");

		List<TransacaoModel> transacoes = transacaoGateway.getTransacoes();
		
		Stream<TransacaoModel> transacoesFiltro = getTransacoesFiltroEstatistica(transacoes);

		EstatisticaModel estatistica = new EstatisticaModel();

		estatistica.setAvg(transacoesFiltro.mapToDouble(transacao -> transacao.getValor().doubleValue()).average()
				.getAsDouble());

		estatistica.setCount(transacoes.size());

		estatistica.setMax(
				transacoesFiltro.mapToDouble(transacao -> transacao.getValor().doubleValue()).max().getAsDouble());

		estatistica.setMin(
				transacoesFiltro.mapToDouble(transacao -> transacao.getValor().doubleValue()).min().getAsDouble());

		estatistica.setSum(transacoesFiltro.mapToDouble(transacao -> transacao.getValor().doubleValue()).sum());

		return estatistica;

	}
	
	Stream<TransacaoModel> getTransacoesFiltroEstatistica(List<TransacaoModel> transacoes){
		
		final long QUANTIDADE_MINUTOS_ESTATISTICA = 1L;
		
		Stream<TransacaoModel> transacoesFiltrada = transacoes.stream()
				.filter(transacao -> transacao.getDataHora().plus(QUANTIDADE_MINUTOS_ESTATISTICA, ChronoUnit.MINUTES)
				.isEqual(LocalDateTime.now().minusMinutes(QUANTIDADE_MINUTOS_ESTATISTICA)));
		
		if(transacoesFiltrada == null) {
			throw new TransacoesNaoEncontradaException("Nenhuma transações com base no filtro de " +  
					QUANTIDADE_MINUTOS_ESTATISTICA + " minuto");
		}
		
		return transacoesFiltrada;
	}

	void verificaExistenciaTransacoes(String possivelMensagemErro) {
		if (transacaoGateway.getTransacoes().isEmpty()) {
			throw new TransacoesNaoEncontradaException(possivelMensagemErro);
		}
	}
}
