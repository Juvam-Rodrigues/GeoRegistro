package br.edu.ufrn.bdnosql.GeoRegistro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.EmailCadastradoException;
import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.SenhaInvalidaException;
import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.TerrenoConflitanteException;
import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.TerrenoNaoEncontradoException;
import br.edu.ufrn.bdnosql.GeoRegistro.exception.custom.UserNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	
	@ExceptionHandler(UserNaoEncontradoException.class)
	public ResponseEntity<ErrorMessage> handleUserNaoEncontrado(UserNaoEncontradoException ex,  HttpServletRequest request){
		ErrorMessage error =  new ErrorMessage(
	            request,
	            HttpStatus.NOT_FOUND,
	            ex.getMessage()
	    );
		
		 return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body(error);
	}
	
	
	@ExceptionHandler(TerrenoNaoEncontradoException.class)
	public ResponseEntity<ErrorMessage> handleTerrenoNaoEncontrado(TerrenoNaoEncontradoException ex,  HttpServletRequest request){
		ErrorMessage error =  new ErrorMessage(
	            request,
	            HttpStatus.NOT_FOUND,
	            ex.getMessage()
	    );
		
		 return ResponseEntity
		            .status(HttpStatus.NOT_FOUND)
		            .body(error);
	}
	
	@ExceptionHandler(TerrenoConflitanteException.class)
	public ResponseEntity<ErrorMessage> handleTerrenoConflitante(TerrenoConflitanteException ex,  HttpServletRequest request){
		ErrorMessage error =  new ErrorMessage(
	            request,
	            HttpStatus.CONFLICT,
	            ex.getMessage()
	    );
		
		 return ResponseEntity
		            .status(HttpStatus.CONFLICT)
		            .body(error);
	}
	
	@ExceptionHandler(SenhaInvalidaException.class)
	public ResponseEntity<ErrorMessage> handleSenhaInvalida(SenhaInvalidaException ex,  HttpServletRequest request){
		ErrorMessage error =  new ErrorMessage(
	            request,
	            HttpStatus.BAD_REQUEST,
	            ex.getMessage()
	    );
		
		 return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(error);
	}
	
	@ExceptionHandler(EmailCadastradoException.class)
	public ResponseEntity<ErrorMessage> handleEmailCadastrado(EmailCadastradoException ex,  HttpServletRequest request){
		ErrorMessage error =  new ErrorMessage(
	            request,
	            HttpStatus.BAD_REQUEST,
	            ex.getMessage()
	    );
		
		 return ResponseEntity
		            .status(HttpStatus.BAD_REQUEST)
		            .body(error);
	}
	
}
	