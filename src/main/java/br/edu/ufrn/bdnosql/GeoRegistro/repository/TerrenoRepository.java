package br.edu.ufrn.bdnosql.GeoRegistro.repository;

import br.edu.ufrn.bdnosql.GeoRegistro.model.Terreno;
import br.edu.ufrn.bdnosql.GeoRegistro.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TerrenoRepository extends MongoRepository<Terreno, String> {
    Optional<Terreno> findByUsuarioId(String usuarioId);
}
