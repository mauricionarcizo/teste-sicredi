package br.com.teste.sicredi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.teste.sicredi.exceptions.AssociadoUnableToVoteException;
import br.com.teste.sicredi.exceptions.AssociadoVotoDuplicatedException;
import br.com.teste.sicredi.exceptions.CpfInvalidException;
import br.com.teste.sicredi.exceptions.IdInvalidException;
import br.com.teste.sicredi.exceptions.PautaNotFoundException;
import br.com.teste.sicredi.exceptions.SessaoNotFoundException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoAlreadyStartedException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoFinishedException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoNotFinishedException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoNotFoundException;
import br.com.teste.sicredi.exceptions.SessaoVotacaoNotStartedException;

@ControllerAdvice
public class ExceptionsAdvice {

	@ResponseBody
	@ExceptionHandler(PautaNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String pautaNotFoundHandler(PautaNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(SessaoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String sessaoNotFoundHandler(SessaoNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(SessaoVotacaoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String sessaoVotacaoNotFound(SessaoVotacaoNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(SessaoVotacaoNotStartedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String sessaoVotacaoNotStarted(SessaoVotacaoNotStartedException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(SessaoVotacaoFinishedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String sessaoVotacaoFinished(SessaoVotacaoFinishedException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(SessaoVotacaoAlreadyStartedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String sessaoVotacaoAlreadyStarted(SessaoVotacaoAlreadyStartedException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(AssociadoVotoDuplicatedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String associadoVotoDuplicatedStarted(AssociadoVotoDuplicatedException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(SessaoVotacaoNotFinishedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String sessaoVotacaoNotFinished(SessaoVotacaoNotFinishedException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(CpfInvalidException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String cpfInvalid(CpfInvalidException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(AssociadoUnableToVoteException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String associadoUnableToVote(AssociadoUnableToVoteException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(IdInvalidException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String idInvalid(IdInvalidException ex) {
		return ex.getMessage();
	}
}
