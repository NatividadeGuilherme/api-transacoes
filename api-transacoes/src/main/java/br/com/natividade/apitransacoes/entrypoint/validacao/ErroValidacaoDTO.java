package br.com.natividade.apitransacoes.entrypoint.validacao;

public class ErroValidacaoDTO {
	private String erro;
	private String campo;

	public ErroValidacaoDTO(String erro, String campo) {
		super();
		this.erro = erro;
		this.campo = campo;
	}
	
	public String getErro() {
		return erro;
	}

	public String getCampo() {
		return campo;
	}

}
