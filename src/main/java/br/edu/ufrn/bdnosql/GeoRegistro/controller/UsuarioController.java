package br.edu.ufrn.bdnosql.GeoRegistro.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufrn.bdnosql.GeoRegistro.dto.UsuarioCadastrarDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.dto.UsuarioLoginDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.model.Usuario;
import br.edu.ufrn.bdnosql.GeoRegistro.service.UsuarioService;

@RestController
@AllArgsConstructor
public class UsuarioController {
	private final UsuarioService usuarioservice;
	
	public UsuarioController(UsuarioService usuarioservice) {
		this.usuarioservice = usuarioservice;
	}
	
	@PostMapping("/cadastrarusuario")
	public ResponseEntity<?> criar(@RequestBody UsuarioCadastrarDTO dto) {

		return ResponseEntity.ok(usuarioservice.cadastrarUsuario(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UsuarioLoginDTO dto) {
		Usuario usuario = usuarioservice.login(dto);

	    return ResponseEntity.ok(usuario.getId());
	}
	
	@GetMapping("/listarusuarios")
	public ResponseEntity<?> listarUsuarios() {
	    return ResponseEntity.ok(usuarioservice.listarUsuarios());
	}
}