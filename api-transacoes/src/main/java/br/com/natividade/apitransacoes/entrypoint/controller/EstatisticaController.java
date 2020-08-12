package br.com.natividade.apitransacoes.entrypoint.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.natividade.apitransacoes.entrypoint.controller.dto.EstatisticaDTO;
import br.com.natividade.apitransacoes.model.EstatisticaModel;
import br.com.natividade.apitransacoes.usecase.EstatisticaUseCase;

@RestController
@RequestMapping("/")
public class EstatisticaController {
	@Autowired
	private EstatisticaUseCase estatisticaUseCase;
	
	@GetMapping("/estatistica")
	public ResponseEntity<EstatisticaDTO> getEstatistica() {
		System.out.println(LocalDateTime.now());
		EstatisticaModel estatistica = estatisticaUseCase.getEstatistica();

		return ResponseEntity.ok(new EstatisticaDTO(estatistica));

	}
}
