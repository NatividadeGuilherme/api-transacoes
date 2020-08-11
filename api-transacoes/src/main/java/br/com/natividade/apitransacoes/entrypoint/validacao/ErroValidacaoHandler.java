package br.com.natividade.apitransacoes.entrypoint.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.natividade.apitransacoes.usecase.exception.TransacoesNaoEncontradaException;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacaoDTO> getErrosRequisicaoInvalida(MethodArgumentNotValidException exception) {
		List<ErroValidacaoDTO> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroValidacaoDTO erro = new ErroValidacaoDTO(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(TransacoesNaoEncontradaException.class)
	public ErroValidacaoDTO getErroTransacoesNaoEncontradas(TransacoesNaoEncontradaException exception) {
		return new ErroValidacaoDTO(exception.getMessage(), "Transações");
	}

	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(Exception.class)
	public void getErroGenerico(Exception exception) {
		System.out.println("Ocorreu o seguinte erro: " + exception.getMessage());
	}

}
