package br.com.natividade.apitransacoes.usecase.gateway;

import java.util.List;

import br.com.natividade.apitransacoes.model.TransacaoModel;

public interface TransacaoGateway {
	void realizarTransacao(TransacaoModel transacao);

	void deletarTransacoes();
	
	List<TransacaoModel> getTransacoes();
}
