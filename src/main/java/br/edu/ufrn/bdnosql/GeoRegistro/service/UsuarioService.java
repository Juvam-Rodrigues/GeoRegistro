package br.edu.ufrn.bdnosql.GeoRegistro.service;

import br.edu.ufrn.bdnosql.GeoRegistro.dto.UsuarioCadastrarDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.dto.UsuarioLoginDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.EmailCadastradoException;
import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.SenhaInvalidaException;
import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.UserNaoEncontradoException;
import br.edu.ufrn.bdnosql.GeoRegistro.model.Usuario;
import br.edu.ufrn.bdnosql.GeoRegistro.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
	private UsuarioRepository usuarioRepository;
	//Classe usada para criptografar as senhas
	private PasswordEncoder encoder;

	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
	}

	public Usuario cadastrarUsuario(UsuarioCadastrarDTO usuarioDTO) {
		if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
			throw new EmailCadastradoException();
		} else {
			Usuario novoUsuario = new Usuario();
			novoUsuario.setEmail(usuarioDTO.getEmail());
			novoUsuario.setNome(usuarioDTO.getNome());
			novoUsuario.setSenha(encoder.encode(usuarioDTO.getSenha()));
			return usuarioRepository.save(novoUsuario);
		}
	}

	public Usuario login(UsuarioLoginDTO usuarioDTO) {

		Usuario usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail())
				.orElseThrow(() -> new UserNaoEncontradoException());
		
		//O usuário passa a senha descriptografada que vai ser comparada com a senha criptografada
		if (!encoder.matches(usuarioDTO.getSenha(), usuario.getSenha())) {
			throw new SenhaInvalidaException();
		}
		else {
			return usuario;	
		}
	}

	public Usuario buscarPorId(String id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new UserNaoEncontradoException());
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
}
