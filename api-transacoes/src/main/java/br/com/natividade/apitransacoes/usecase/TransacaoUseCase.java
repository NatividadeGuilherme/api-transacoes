package br.com.natividade.apitransacoes.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

		if (transacaoGateway.getTransacoes().isEmpty()) {
			throw new TransacoesNaoEncontradaException("Nenhuma transação encontrada para ser deletada!");
		}
		
		transacaoGateway.deletarTransacoes();
	}

}
