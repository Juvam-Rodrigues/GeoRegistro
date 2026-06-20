package br.edu.ufrn.bdnosql.GeoRegistro.service;

import br.edu.ufrn.bdnosql.GeoRegistro.dto.TerrenoDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.model.Terreno;
import br.edu.ufrn.bdnosql.GeoRegistro.repository.TerrenoRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.geo.Point;
import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.PolygonArea;
import net.sf.geographiclib.PolygonResult;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TerrenoService {
	private TerrenoRepository terrenoRepository;

	public Terreno cadastrarTerreno(TerrenoDTO dto) {

		List<Point> pontos = dto.getCoordenadas().stream().map(coordenada ->
		new Point(coordenada.getLongitude(), coordenada.getLatitude())).toList();

		// GeoJSON exige que o primeiro ponto seja igual ao último
		if (!pontos.get(0).equals(pontos.get(pontos.size() - 1))) {
			pontos = new ArrayList<>(pontos);
			pontos.add(pontos.get(0));
		}

		GeoJsonPolygon poligono = new GeoJsonPolygon(pontos);
		
		if(terrenoRepository.buscarIntersecoes(poligono)) {
			throw new RuntimeException("Terreno já cadastrado...");
		}
		else {
			Terreno terreno = new Terreno();
			
			terreno.setUsuarioId(dto.getUserId());
			terreno.setPoligono(poligono);
			terreno.setStatusOcupacao(true);

			return terrenoRepository.save(terreno);
		}
	}

	public double calcularArea(GeoJsonPolygon poligono) { // to Fix

		PolygonArea polygon = new PolygonArea(Geodesic.WGS84, false);

		List<Point> pontos = poligono.getPoints();

		for (Point p : pontos) {

			double lon = p.getX();
			double lat = p.getY();

			polygon.AddPoint(lat, lon);
		}

		PolygonResult result = polygon.Compute();

		return Math.abs(result.area);
	}
}
