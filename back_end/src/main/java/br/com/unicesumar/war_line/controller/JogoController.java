package br.com.unicesumar.war_line.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unicesumar.war_line.repository.JogoRepository;

@RequestMapping("/api/jogo")
@RestController
@CrossOrigin("*")
public class JogoController {
	
	@Autowired
	JogoRepository jogoRepository;
	
	@GetMapping
	public Integer buscarRodadaAtual() {
		return jogoRepository.findById(1).get().getRodada();
	}
	
}
