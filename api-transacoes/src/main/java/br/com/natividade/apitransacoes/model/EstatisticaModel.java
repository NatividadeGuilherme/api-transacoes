package br.com.natividade.apitransacoes.model;

import java.math.BigDecimal;

public class EstatisticaModel {
	private int count;
	private BigDecimal sum;
	private double avg;
	private BigDecimal min;
	private BigDecimal max;

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

	public void setCount(int count) {
		this.count = count;
	}

	public void setSum(double sum) {
		this.sum = new BigDecimal(sum);
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public void setMin(double min) {
		this.min = new BigDecimal(min);
	}

	public void setMax(double max) {
		this.max = new BigDecimal(max);
	}

}
