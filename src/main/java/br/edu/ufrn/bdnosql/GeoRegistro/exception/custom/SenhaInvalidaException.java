package br.edu.ufrn.bdnosql.GeoRegistro.exception.custom;

public class SenhaInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SenhaInvalidaException() {
        super("Senha inválida.");
    }
}
