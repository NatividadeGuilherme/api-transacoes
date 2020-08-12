package br.com.natividade.apitransacoes.entrypoint.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.natividade.apitransacoes.entrypoint.controller.form.TransacaoForm;
import br.com.natividade.apitransacoes.usecase.TransacaoUseCase;

@RestController
@RequestMapping("/")
public class TransacaoController {
	@Autowired
	private TransacaoUseCase transacaoUseCase;
	
	@PostMapping("/transacao")
	public ResponseEntity<?> realizarTransacao(@RequestBody @Valid TransacaoForm transacaoForm){
		
		transacaoUseCase.realizarTransacao(transacaoForm.converter());
		
		return ResponseEntity.status(HttpStatus.CREATED).body("");
	}
	
	@DeleteMapping("/transacao")
	public ResponseEntity<?> deletarTransacoes(){
		transacaoUseCase.deletarTransacoes();
		
		return ResponseEntity.ok("Transações apagadas!");
	}
	
}
