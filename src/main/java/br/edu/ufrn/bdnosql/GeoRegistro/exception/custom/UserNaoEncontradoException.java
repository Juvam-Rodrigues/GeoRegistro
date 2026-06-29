package br.edu.ufrn.bdnosql.GeoRegistro.exception.custom;

public class UserNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UserNaoEncontradoException() {
        super("Usuário não encontrado.");
    }
	
}
