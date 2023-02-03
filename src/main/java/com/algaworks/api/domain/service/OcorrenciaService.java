package com.algaworks.api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.model.Ocorrencia;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OcorrenciaService {

	private EntregaService entregaService;
	
	@Transactional
	public Ocorrencia saveOcorrencia(Long entregaId, String descricao) {
		Entrega entrega = entregaService.findById(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}
	
	public List<Ocorrencia> findAllById(Long id){
		Entrega entrega = entregaService.findById(id);
		return entrega.getOcorrencias();
		
	}
}
