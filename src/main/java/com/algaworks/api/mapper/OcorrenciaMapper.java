package com.algaworks.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.api.domain.dto.OcorrenciaDTO;
import com.algaworks.api.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {

	private ModelMapper modelMapper;
	
	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}
	
	public List<OcorrenciaDTO> toListDTO(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}
}
