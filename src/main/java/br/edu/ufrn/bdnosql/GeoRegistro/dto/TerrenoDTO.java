package br.edu.ufrn.bdnosql.GeoRegistro.dto;

import java.util.List;

//Classe responsável para receber os dados que está vindo da aplicação do celular
public class TerrenoDTO {
	private String usuarioId;
	private List<CoordenadaDTO> coordenadas;
	
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUserId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public List<CoordenadaDTO> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<CoordenadaDTO> coordenadas) {
		this.coordenadas = coordenadas;
	}
}
