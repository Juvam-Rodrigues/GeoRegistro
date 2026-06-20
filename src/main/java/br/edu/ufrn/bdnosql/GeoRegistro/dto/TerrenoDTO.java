package br.edu.ufrn.bdnosql.GeoRegistro.dto;

import java.util.List;

//Classe responsável para receber os dados que está vindo da aplicação do celular
public class TerrenoDTO {
	private String userId;
	private List<CoordenadaDTO> coordenadas;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<CoordenadaDTO> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<CoordenadaDTO> coordenadas) {
		this.coordenadas = coordenadas;
	}
}
