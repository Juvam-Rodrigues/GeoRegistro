package br.edu.ufrn.bdnosql.GeoRegistro.service;

import br.edu.ufrn.bdnosql.GeoRegistro.model.Usuario;
import br.edu.ufrn.bdnosql.GeoRegistro.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario novoUsuario) {
        if(usuarioRepository.existsByEmail(novoUsuario.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        } else {
            return usuarioRepository.save(novoUsuario);
        }
    }

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if(!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha inválida");
        }

        return usuario;
    }

    public Usuario buscarPorId(String id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
