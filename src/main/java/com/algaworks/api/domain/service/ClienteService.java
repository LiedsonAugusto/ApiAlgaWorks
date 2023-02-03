package com.algaworks.api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.algaworks.api.domain.exceptions.EmailJaExistente;
import com.algaworks.api.domain.exceptions.ObjetoNaoEncontrado;
import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteService {

	private ClienteRepository clienteRespository;
	
	public List<Cliente> findAll(){
		return clienteRespository.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRespository.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado(id));
	}
	
	@Transactional
	public void deleteById(Long id) {
		Cliente cliente = clienteRespository.findById(id).orElseThrow(() -> new ObjetoNaoEncontrado(id));
		clienteRespository.deleteById(cliente.getId());
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		Optional<Cliente> obj = clienteRespository.findByEmail(cliente.getEmail());
		if (obj.isPresent() && !(obj.get().equals(cliente))) {throw new EmailJaExistente();}
		return clienteRespository.save(cliente);
	}
	
	public Cliente update(Long id, Cliente cliente) {
		if (!clienteRespository.existsById(id)) {
			throw new ObjetoNaoEncontrado(id);
		}
		cliente.setId(id);
		return insert(cliente);
	}
	
}
