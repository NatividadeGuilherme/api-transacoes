package br.com.natividade.apitransacoes.entrypoint.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import br.com.natividade.apitransacoes.model.TransacaoModel;

public class TransacaoForm {
	
	@DecimalMin("0.0")
	@NotNull(message = "O campo valor é obrigatório")
	private BigDecimal valor;

	@NotNull(message = "O campo data hora é obrigatório")
	@Past(message = "O campo dataHora recebe uma data do futuro")
	private LocalDateTime dataHora;

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	public TransacaoModel converter() {
		return new TransacaoModel(dataHora, valor);
	}
}
