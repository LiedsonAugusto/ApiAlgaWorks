package com.algaworks.api.domain.exceptions;

public class EmailJaExistente extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EmailJaExistente() {
		super("Email já foi registrado");
	}

}
