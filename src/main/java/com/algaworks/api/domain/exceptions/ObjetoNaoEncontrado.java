package com.algaworks.api.domain.exceptions;

public class ObjetoNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjetoNaoEncontrado(Long id) {
		super("Objeto com ID: " + id + " Não encontrado");
	}

}
