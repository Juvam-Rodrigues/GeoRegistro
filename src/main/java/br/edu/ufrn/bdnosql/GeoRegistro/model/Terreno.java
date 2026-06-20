package br.edu.ufrn.bdnosql.GeoRegistro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "terrenos")
public class Terreno {
    @Id
    private String id;
    @Indexed
    private String usuarioId;
    private double areaM2;
    private boolean statusOcupacao = false;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPolygon poligono;

    public Terreno() {
    	
    }
    public Terreno(String usuarioId, GeoJsonPolygon poligono) {
    	this.usuarioId = usuarioId;
        this.areaM2 = 0;
        this.poligono = poligono;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(double areaM2) {
        this.areaM2 = areaM2;
    }

    public boolean isStatusOcupacao() {
        return statusOcupacao;
    }

    public void setStatusOcupacao(boolean statusOcupacao) {
        this.statusOcupacao = statusOcupacao;
    }

    public GeoJsonPolygon getPoligono() {
        return poligono;
    }

    public void setPoligono(GeoJsonPolygon geometria) {
        this.poligono = geometria;
    }
}
