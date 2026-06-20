package br.edu.ufrn.bdnosql.GeoRegistro.repository;

import br.edu.ufrn.bdnosql.GeoRegistro.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
