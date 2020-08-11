package br.com.natividade.apitransacoes.usecase.exception;

public class TransacoesNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = -5201695370569198247L;
	
	public TransacoesNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}
