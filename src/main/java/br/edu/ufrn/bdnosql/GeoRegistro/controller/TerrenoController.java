package br.edu.ufrn.bdnosql.GeoRegistro.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.bdnosql.GeoRegistro.dto.TerrenoCadastrarDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.service.TerrenoService;


@RestController
@AllArgsConstructor
public class TerrenoController {
	private TerrenoService terrenoService;
	
	@PostMapping("/cadastrarterrenos")
	public ResponseEntity<?> criar(@RequestBody TerrenoCadastrarDTO dto) {

		return ResponseEntity.ok(terrenoService.cadastrarTerreno(dto));
	}
	
	@GetMapping("/listarterrenos")
	public ResponseEntity<?> listarTodos() {
		List<?> resultado = terrenoService.listarTodos();

		return ResponseEntity.ok(resultado);
	}
}
