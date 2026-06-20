package br.edu.ufrn.bdnosql.GeoRegistro.repository;

import br.edu.ufrn.bdnosql.GeoRegistro.dto.TerrenoDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.model.Terreno;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TerrenoRepository extends MongoRepository<Terreno, String> {
    Optional<Terreno> findByUsuarioId(String usuarioId);
    @Query("{ poligono: { $geoIntersects: { $geometry: ?0 } } }")
    List<Terreno> buscarIntersecoes(GeoJsonPolygon geometria);
}
