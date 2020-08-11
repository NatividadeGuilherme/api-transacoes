package br.com.natividade.apitransacoes.entrypoint.controller.dto;

import java.math.BigDecimal;

import br.com.natividade.apitransacoes.model.EstatisticaModel;

public class EstatisticaDTO {
	private int count;
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

	public int getCount() {
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

	public EstatisticaDTO(int count, BigDecimal sum, double avg, BigDecimal min, BigDecimal max) {
		super();
		this.count = count;
		this.sum = sum;
		this.avg = avg;
		this.min = min;
		this.max = max;
	}

}
