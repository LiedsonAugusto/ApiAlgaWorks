package com.algaworks.api.resources.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.api.domain.dto.OcorrenciaDTO;
import com.algaworks.api.domain.input.OcorrenciaInput;
import com.algaworks.api.domain.model.Ocorrencia;
import com.algaworks.api.domain.service.OcorrenciaService;
import com.algaworks.api.mapper.OcorrenciaMapper;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("entregas/{id}/ocorrencias")
public class OcorrenciaController {
	
	private OcorrenciaService ocorrenciaService;
	private OcorrenciaMapper ocorrenciaMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO saveOcorrencia(@PathVariable Long id,@Valid @RequestBody OcorrenciaInput ocorrencia) {
		Ocorrencia ocorrenciaRegistrada = ocorrenciaService.saveOcorrencia(id, ocorrencia.getDescricao());
		return ocorrenciaMapper.toDTO(ocorrenciaRegistrada);
		
	}
	
	@GetMapping
	public List<OcorrenciaDTO> findAllById(@PathVariable Long id){
		return ocorrenciaMapper.toListDTO(ocorrenciaService.findAllById(id));
	}
}
