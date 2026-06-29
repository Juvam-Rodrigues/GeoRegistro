package br.edu.ufrn.bdnosql.GeoRegistro.exception.custom;

public class EmailCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmailCadastradoException() {
		super("E-mail já cadastrado.");
	}
}
