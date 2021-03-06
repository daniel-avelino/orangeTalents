package com.zup.orangeTalents.resources.exceptions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public ResponseEntity<String> userNotFound(NotFoundException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
	}

	@ExceptionHandler(MarcasException.class)
	public ResponseEntity<String> marcasException(MarcasException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Marca não encontrada, revise o nome inserido");
	}

	@ExceptionHandler(ModelosException.class)
	public ResponseEntity<String> marcasException(ModelosException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo não encontrado, revise o nome inserido");
	}

	@ExceptionHandler(AnoException.class)
	public ResponseEntity<String> marcasException(AnoException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ano não encontrado, revise o nome inserido");
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	public ResponseEntity<String> dateException(HttpMessageConversionException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data inválida (dd/MM/yyyy)");
	}

	@ExceptionHandler(ConstraintException.class)
	public ResponseEntity<String> ConstraintException(ConstraintException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado.");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		BindingResult result = e.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Problemas com a validação: " + fieldErrors.get(0).getDefaultMessage());
	}
}