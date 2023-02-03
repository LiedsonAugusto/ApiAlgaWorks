package com.algaworks.api.resources.excpetionsHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.api.domain.exceptions.EmailJaExistente;
import com.algaworks.api.domain.exceptions.ObjetoNaoEncontrado;
import com.algaworks.api.domain.exceptions.StatusPendenteException;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

	// valid exception
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		StandartError error = new StandartError();
		List<StandartError.Campo> campos = new ArrayList<>();
		
		for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) erro).getField();
			String mensagem = erro.getDefaultMessage();
			campos.add(new StandartError.Campo(nome, mensagem));
		}
		
		error.setStatus(status.value());
		error.setDate(OffsetDateTime.now());
		error.setTitulo("Argumentos passados são inválidos, conserte e tente novamente");
		error.setCampos(campos);
		return super.handleExceptionInternal(ex, error, headers, status, request);
	}
	
	@ExceptionHandler(ObjetoNaoEncontrado.class)
	public ResponseEntity<Object> handleObjetoNaoEncontrado(ObjetoNaoEncontrado er, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandartError error = new StandartError();
		error.setStatus(status.value());
		error.setDate(OffsetDateTime.now());
		error.setTitulo(er.getMessage());
		return super.handleExceptionInternal(er, error, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EmailJaExistente.class)
	public ResponseEntity<Object> handleEmailJaExistente(EmailJaExistente er, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError error = new StandartError();
		error.setStatus(status.value());
		error.setDate(OffsetDateTime.now());
		error.setTitulo(er.getMessage());
		return super.handleExceptionInternal(er, error, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(StatusPendenteException.class)
	public ResponseEntity<Object> handleStatusPendente(StatusPendenteException er, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandartError error = new StandartError();
		error.setStatus(status.value());
		error.setDate(OffsetDateTime.now());
		error.setTitulo(er.getMessage());
		return super.handleExceptionInternal(er, error, new HttpHeaders(), status, request);
	}
	
	
}
