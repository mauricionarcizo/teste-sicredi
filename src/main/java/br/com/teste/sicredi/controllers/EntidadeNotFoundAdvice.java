package br.com.teste.sicredi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.teste.sicredi.exceptions.EntidadeNotFoundException;

@ControllerAdvice
public class EntidadeNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(EntidadeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String entidadeNotFoundHandler(EntidadeNotFoundException ex) {
		return ex.getMessage();
	}
}
