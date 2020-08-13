package br.com.natividade.apitransacoes.dataprovider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.natividade.apitransacoes.dataprovider.repository.TransacaoRepository;
import br.com.natividade.apitransacoes.model.TransacaoModel;
import br.com.natividade.apitransacoes.usecase.gateway.TransacaoGateway;

@Component
public class TransacaoDataProvider implements TransacaoGateway {

	@Autowired 
	private TransacaoRepository repository;
	
	@Override
	public void realizarTransacao(TransacaoModel transacao) {
		repository.realizarTransacao(transacao);
	}
	
	@Override
	public void deletarTransacoes() {
		repository.deletarTransacoes();
		
	}

	@Override
	public List<TransacaoModel> getTransacoes() {
		return repository.getTransacoes();
	}

}
