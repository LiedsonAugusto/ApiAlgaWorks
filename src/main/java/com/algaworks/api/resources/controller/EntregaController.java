package com.algaworks.api.resources.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.dto.EntregaDTO;
import com.algaworks.api.domain.input.EntregaInput;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.service.EntregaService;
import com.algaworks.api.mapper.EntregaMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/entregas")
@AllArgsConstructor
public class EntregaController {

	private EntregaService entregaService;
	private EntregaMapper entregaMapper;
	
	@GetMapping
	public ResponseEntity<List<EntregaDTO>> findAll(){
		return ResponseEntity.ok().body(entregaMapper.toListDTO(entregaService.findAll()));
	}
	
	@GetMapping(value  = "/{id}")
	public ResponseEntity<EntregaDTO> findById(@PathVariable Long id){
		Entrega entrega = entregaService.findById(id);
		EntregaDTO entregaDTO = entregaMapper.toDTO(entrega);
		return ResponseEntity.ok().body(entregaDTO);
		
	}
	
	@PostMapping
	public ResponseEntity<EntregaDTO> insert(@Valid @RequestBody EntregaInput entregaInput){
		Entrega entrega = entregaMapper.toEntrega(entregaInput);
		return ResponseEntity.status(HttpStatus.CREATED).body(entregaMapper.toDTO(entregaService.saveEntrega(entrega)));
	}
	
	@PostMapping(value = "/{id}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long id) {
		entregaService.finalizarEntrega(id);
	}
	
}
