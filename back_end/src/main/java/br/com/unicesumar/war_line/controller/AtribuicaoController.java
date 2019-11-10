package br.com.unicesumar.war_line.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicesumar.war_line.dto.AtribuicaoDto;
import br.com.unicesumar.war_line.modelo.Atribuicao;
import br.com.unicesumar.war_line.repository.AtribuicaoRepository;

@RequestMapping("/api/atribuicoes")
@RestController
@CrossOrigin("*")
public class AtribuicaoController {

	@Autowired
	AtribuicaoRepository atribuicaoRepository;
	
	@GetMapping
	public List<AtribuicaoDto> listar() {
		 List<Atribuicao> atribuicoes = atribuicaoRepository.findAll();
		 return AtribuicaoDto.converter(atribuicoes);
	}
	
	@GetMapping("/{id}")
	public AtribuicaoDto buscarPeloId(@PathVariable("id") Long id) {
		return new AtribuicaoDto(atribuicaoRepository.findById(id).get());
	}
}
