package br.com.unicesumar.war_line.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicesumar.war_line.dto.EstadoDto;
import br.com.unicesumar.war_line.modelo.Estado;
import br.com.unicesumar.war_line.repository.EstadoRepository;

@RequestMapping("/api/estado")
@RestController
@CrossOrigin("*")
public class EstadoController {
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@GetMapping
	public List<EstadoDto> getAll() {
		List<Estado> estados = estadoRepository.findAll();
		return EstadoDto.converter(estados);
	}
	
	@GetMapping("busca-pelo-nome/{id}")
	public EstadoDto getOne(@PathVariable("id") Long id) {
		return new EstadoDto(estadoRepository.findById(id).get());
	}

	@GetMapping("busca-pelo-uf/{uf}")
	public EstadoDto buscaPeloUf(@PathVariable("uf") String uf) {
		return new EstadoDto(estadoRepository.findByUf(uf));
	}
}
