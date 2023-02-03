package com.algaworks.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.api.domain.dto.EntregaDTO;
import com.algaworks.api.domain.input.EntregaInput;
import com.algaworks.api.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaMapper {

	private ModelMapper modelMapper;
	
	public EntregaDTO toDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}
	
	public List<EntregaDTO> toListDTO(List<Entrega> entregas){
		return entregas.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntrega(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
