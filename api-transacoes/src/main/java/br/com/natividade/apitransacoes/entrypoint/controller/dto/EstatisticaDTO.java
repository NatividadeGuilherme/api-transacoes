package br.com.natividade.apitransacoes.entrypoint.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

	public EstatisticaDTO(int count, BigDecimal sum, double avg, BigDecimal min, BigDecimal max) {
		super();
		this.count = count;
		this.sum = sum;
		this.avg = avg;
		this.min = min;
		this.max = max;
	}
	
	public static void main(String[] args) {
		LocalDateTime dataHora1 = LocalDateTime.of(2020, 8, 11, 20, 54, 02);
		LocalDateTime dataHora2 = LocalDateTime.of(2020, 8, 11, 20, 53, 01);
		
		long resultado = ChronoUnit.SECONDS.between(dataHora2, dataHora1);
		
		System.out.println(resultado);
		
	}

}
