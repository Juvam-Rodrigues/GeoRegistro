package br.edu.ufrn.bdnosql.GeoRegistro.controller;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.bdnosql.GeoRegistro.dto.TerrenoDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.service.TerrenoService;


@RestController
@AllArgsConstructor
public class TerrenoController {
	private TerrenoService terrenoService;
	
	@PostMapping("/terrenos")
	public ResponseEntity<?> criar(@RequestBody TerrenoDTO dto) {

		return ResponseEntity.ok(terrenoService.cadastrarTerreno(dto));
	}
	
	@GetMapping("/listarterrenos")
	public ResponseEntity<?> listarTodos() {
		List<?> resultado = terrenoService.listarTodos();

		return ResponseEntity.ok(resultado);
	}
}
