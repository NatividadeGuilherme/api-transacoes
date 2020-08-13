package br.com.natividade.apitransacoes.entrypoint.controller.dto;

import java.math.BigDecimal;

import br.com.natividade.apitransacoes.model.EstatisticaModel;

public class EstatisticaDTO {
	private long count;
	private BigDecimal sum;
	private double avg;
	private BigDecimal min;
	private BigDecimal max;

	public EstatisticaDTO(EstatisticaModel estatisticaModel) {
		count = estatisticaModel.getCount();
		sum = estatisticaModel.getSum();
		avg = estatisticaModel.getAvg();
		min = estatisticaModel.getMin();
		max = estatisticaModel.getMax();
	}

	public long getCount() {
		return count;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public double getAvg() {
		return avg;
	}

	public BigDecimal getMin() {
		return min;
	}

	public BigDecimal getMax() {
		return max;
	}

}
