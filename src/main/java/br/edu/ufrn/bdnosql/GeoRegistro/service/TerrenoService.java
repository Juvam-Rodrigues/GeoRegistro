package br.edu.ufrn.bdnosql.GeoRegistro.service;

import br.edu.ufrn.bdnosql.GeoRegistro.model.Terreno;
import br.edu.ufrn.bdnosql.GeoRegistro.repository.TerrenoRepository;
import com.mongodb.client.model.geojson.Point;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TerrenoService {
    private TerrenoRepository terrenoRepository;

    /*public Terreno cadastrarTerreno(Terreno novoTerreno) {
        implementar
    }*/

    public double calcularArea(GeoJsonPolygon geometria) { // to Fix

        PolygonArea polygon =
                new PolygonArea(Geodesic.WGS84, false);

        List<Point> pontos = geometria.getPoints();

        for (Point p : pontos) {

            double lon = p.getX();
            double lat = p.getY();

            polygon.AddPoint(lat, lon);
        }

        PolygonResult result = polygon.Compute();

        return Math.abs(result.area);
    }
}
