package br.com.carlosborges.controller.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandartError> objectNotFound(NoSuchElementException e , HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartError err = new StandartError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
 