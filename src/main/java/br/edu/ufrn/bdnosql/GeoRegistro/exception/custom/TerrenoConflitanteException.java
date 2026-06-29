package br.edu.ufrn.bdnosql.GeoRegistro.exception.custom;

public class TerrenoConflitanteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public TerrenoConflitanteException() {
		super("Terreno conflituoso com outra área já cadastrada.");
	}

}
