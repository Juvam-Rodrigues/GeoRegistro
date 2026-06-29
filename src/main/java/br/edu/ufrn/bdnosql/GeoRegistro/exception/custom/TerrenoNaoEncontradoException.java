package br.edu.ufrn.bdnosql.GeoRegistro.exception.custom;

public class TerrenoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TerrenoNaoEncontradoException() {
        super("Não há terrenos cadastrados.");
    }
}
