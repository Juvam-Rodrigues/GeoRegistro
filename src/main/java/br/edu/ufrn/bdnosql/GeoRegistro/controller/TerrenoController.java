package br.edu.ufrn.bdnosql.GeoRegistro.controller;

import lombok.AllArgsConstructor;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
}
