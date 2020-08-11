package br.com.natividade.apitransacoes.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoModel {

	private LocalDateTime dataHora;
	private BigDecimal valor;
	
	public TransacaoModel(LocalDateTime dataHora, BigDecimal valor) {
		super();
		this.dataHora = dataHora;
		this.valor = valor;
	}
	
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
}
