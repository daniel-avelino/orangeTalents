package com.zup.orangeTalents.resources.exceptions;

import java.io.IOException;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zup.orangeTalents.services.exceptions.AnoException;
import com.zup.orangeTalents.services.exceptions.ConstraintException;
import com.zup.orangeTalents.services.exceptions.MarcasException;
import com.zup.orangeTalents.services.exceptions.ModelosException;
import com.zup.orangeTalents.services.exceptions.NotFoundException;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> userNotFound(NotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Usuário não encontrado");
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MarcasException.class)
	public ResponseEntity<StandardError> marcasException(MarcasException e, HttpServletRequest request)
			throws IOException {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Marca não encontrada, revise o nome inserido");
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ModelosException.class)
	public ResponseEntity<StandardError> marcasException(ModelosException e, HttpServletRequest request)
			throws IOException {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Modelo não encontrado, revise o nome inserido");
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(AnoException.class)
	public ResponseEntity<StandardError> marcasException(AnoException e, HttpServletRequest request)
			throws IOException {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Ano não encontrado, revise o nome inserido");
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<StandardError> dateException(HttpMessageConversionException e, HttpServletRequest request)
			throws IOException {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Data inválida (dd/MM/yyyy)");
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<StandardError> ConstraintException(ConstraintException e, HttpServletRequest request)
			throws IOException {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Usuário já cadastrado.");
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation exception");
		return ResponseEntity.status(status).body(err);
	}
}