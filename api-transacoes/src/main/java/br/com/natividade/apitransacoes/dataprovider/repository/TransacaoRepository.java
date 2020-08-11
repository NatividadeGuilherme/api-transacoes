package br.com.natividade.apitransacoes.dataprovider.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.natividade.apitransacoes.model.TransacaoModel;

@Repository
public class TransacaoRepository {
	private static List<TransacaoModel> transacoes = new ArrayList<>();
	
	public void realizarTransacao(TransacaoModel transacao) {
		transacoes.add(transacao);
	}
	
	public List<TransacaoModel> getTransacoes(){
		return transacoes;
	}

	public void deletarTransacoes() {
		transacoes.clear();
	}
}
