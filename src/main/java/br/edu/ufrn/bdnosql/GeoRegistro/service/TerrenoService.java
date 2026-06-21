package br.edu.ufrn.bdnosql.GeoRegistro.service;

import br.edu.ufrn.bdnosql.GeoRegistro.dto.TerrenoCadastrarDTO;
import br.edu.ufrn.bdnosql.GeoRegistro.model.Terreno;

import br.edu.ufrn.bdnosql.GeoRegistro.repository.TerrenoRepository;
import br.edu.ufrn.bdnosql.GeoRegistro.repository.UsuarioRepository;

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
	private UsuarioRepository usuarioRepository;


	public Terreno cadastrarTerreno(TerrenoCadastrarDTO terrenoDTO) {

		// Verifica se o usuário informado existe e tem ID
		usuarioRepository
        .findById(terrenoDTO.getUsuarioId())
        .orElseThrow(() ->
            new RuntimeException("Usuário não encontrado"));
		
		
		List<Point> pontos = terrenoDTO.getCoordenadas().stream().map(coordenada ->
		new Point(coordenada.getLongitude(), coordenada.getLatitude())).toList();

		// GeoJSON exige que o primeiro ponto seja igual ao último
		if (!pontos.get(0).equals(pontos.get(pontos.size() - 1))) {
			pontos = new ArrayList<>(pontos);
			pontos.add(pontos.get(0));
		}

		GeoJsonPolygon poligono = new GeoJsonPolygon(pontos);
        List<Terreno> conflitos = terrenoRepository.buscarIntersecoes(poligono);

        if(conflitos.isEmpty()){
            Terreno terreno = new Terreno();

            terreno.setUsuarioId(terrenoDTO.getUsuarioId());
            terreno.setPoligono(poligono);
            terreno.setStatusOcupacao(true);
            terreno.setAreaM2(calcularArea(terrenoDTO));

            return terrenoRepository.save(terreno);
        } else {
            throw new RuntimeException("Terreno conflituoso com outra área já cadastrada.");
        }

	}

    public double calcularArea(TerrenoCadastrarDTO dto) {
        PolygonArea polygon = new PolygonArea(Geodesic.WGS84, false);

        List<Point> pontos = dto.getCoordenadas().stream()
                .map(c -> new Point(c.getLongitude(), c.getLatitude()))
                .toList();

        for (Point p : pontos) {
            polygon.AddPoint(p.getY(), p.getX()); // lat, lon
        }

        PolygonResult result = polygon.Compute();

        return Math.abs(result.area);
    }
    
    public List<Terreno> listarTodos(){
    	List<Terreno> terrenos = terrenoRepository.findAll();
    	if(!terrenos.isEmpty()) {
    		return terrenos;
    	}
    	else {
            throw new RuntimeException("Não há terrenos cadastrados.");
    	}
    }
}
