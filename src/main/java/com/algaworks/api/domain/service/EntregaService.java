package com.algaworks.api.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.algaworks.api.domain.exceptions.ObjetoNaoEncontrado;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.model.StatusEntrega;
import com.algaworks.api.domain.repository.EntregaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

	private EntregaRepository entregaRepository;
	private ClienteService clienteService;
	
	public List<Entrega> findAll(){
		return entregaRepository.findAll();
	}
	
	public Entrega findById(Long id) {
		return entregaRepository.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado(id));
	}
	
	@Transactional
	public Entrega saveEntrega(Entrega entrega) {
		Cliente cliente = clienteService.findById(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega.setStatus(StatusEntrega.PENDENTE);
		return entregaRepository.save(entrega);
	}
	
	@Transactional
	public void finalizarEntrega(Long id) {
		Entrega entrega = this.findById(id);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}
	
}
